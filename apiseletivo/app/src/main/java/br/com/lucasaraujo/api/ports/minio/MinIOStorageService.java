package br.com.lucasaraujo.api.ports.minio;

import br.com.lucasaraujo.service.Resource;
import br.com.lucasaraujo.service.StorageService;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.DeleteObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

public class MinIOStorageService implements StorageService {
    private final String bucket;
    private final MinioClient minioClient;
    private final String ip_public;

    public MinIOStorageService(String bucket, MinioClient minioClient, String ipPublic) {
        this.bucket = bucket;
        this.minioClient = minioClient;
        ip_public = ipPublic;
    }

    @Override
    public void store(String id, Resource resource) {
        try{
            ByteArrayInputStream bais = new ByteArrayInputStream(resource.content());
            final var info = PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(id)
                    .contentType(resource.contentType())
                    .stream(bais, bais.available(), -1)
                    .build();

            minioClient.putObject(info);

        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Resource> get(String id) {
        GetObjectArgs objArgs = GetObjectArgs.builder()
                .bucket(bucket)
                .object(id)
                .build();

        try {
            var object = minioClient.getObject(objArgs);
            byte[] content = object.readAllBytes();
            String checksum = object.headers().get("ETag");
            String contentType = object.headers().get("Content-Type");
            String name = id;
            return Optional.of(Resource.with(content, checksum, contentType, name));

        } catch (ErrorResponseException | InsufficientDataException | InternalException
                 | InvalidKeyException | InvalidResponseException | IOException
                 | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            throw new RuntimeException("Erro ao recuperar o objeto do MinIO: " + e.getMessage(), e);
        }
    }

    @Override
    public List<String> list(String prefix) {
        return List.of();
    }

    @Override
    public void deleteAll(List<String> ids) {
        final var idsArg = ids.stream().map(id -> new DeleteObject(id)).toList();
        RemoveObjectsArgs rmvArg= RemoveObjectsArgs.builder().bucket(bucket).objects(idsArg).build();
        minioClient.removeObjects(rmvArg);
    }

    @Override
    public String generateTemporaryLink(String id) {
        int expirationTimeSeconds= 300;
        final var args = GetPresignedObjectUrlArgs
                .builder()
                .expiry(expirationTimeSeconds)
                .method(Method.GET)
                .bucket(bucket)
                .object(id)
                .build();

        try{
            String url = minioClient.getPresignedObjectUrl(args);
            System.out.println(url);
            System.out.println( url.replace("http://minio:9000",ip_public));

            return url.replace("http://minio:9000",ip_public);
        }catch(ErrorResponseException | InsufficientDataException | InternalException
               | InvalidKeyException | InvalidResponseException | IOException
               | NoSuchAlgorithmException | ServerException | XmlParserException e){
            throw new RuntimeException("Erro ao gerar url temporaria: " + e.getMessage(), e);
        }
    }
}
