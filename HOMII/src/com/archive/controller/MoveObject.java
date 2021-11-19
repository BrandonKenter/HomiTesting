package com.archive.controller;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.CopyWriter;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class MoveObject {
  public static void moveObject(
      String projectId,
      String sourceBucketName,
      String sourceObjectName,
      String targetBucketName,
      String targetObjectName) {
    // The ID of your GCP project
    // String projectId = "your-project-id";

    // The ID of your GCS bucket
    // String bucketName = "your-unique-bucket-name";

    // The ID of your GCS object
    // String sourceObjectName = "your-object-name";

    // The ID of the bucket to move the object objectName to
    // String targetBucketName = "target-object-bucket"

    // The ID of your GCS object
    // String targetObjectName = "your-new-object-name";

    Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
    Blob blob = storage.get(sourceBucketName, sourceObjectName);
    // Write a copy of the object to the target bucket
    CopyWriter copyWriter = blob.copyTo(targetBucketName, targetObjectName);
    Blob copiedBlob = copyWriter.getResult();
    // Delete the original blob now that we've copied to where we want it, finishing the "move"
    // operation
    blob.delete();

    System.out.println(
        "Moved object "
            + sourceObjectName
            + " from bucket "
            + sourceBucketName
            + " to "
            + targetObjectName
            + " in bucket "
            + copiedBlob.getBucket());
  }
}