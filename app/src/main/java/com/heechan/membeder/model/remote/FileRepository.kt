package com.heechan.membeder.model.remote

import android.content.Context
import android.net.Uri
import com.heechan.membeder.model.data.file.FileUploadRes
import retrofit2.Response

interface FileRepository {
    suspend fun uploadImage(imageUri : Uri, context : Context): Response<FileUploadRes>
}