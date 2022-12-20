package com.heechan.membeder.model.remote

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.loader.content.CursorLoader
import com.heechan.membeder.model.data.file.FileUploadRes
import com.heechan.membeder.model.service.FileService
import com.heechan.membeder.model.service.RetrofitClient
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.create
import java.io.File

class FilePepositoryImpl : FileRepository {
    private val fileService = RetrofitClient.getRetrofit().create(FileService::class.java)

    private fun getFilePathFromUri(contentUri: Uri, context: Context): String {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val loader = CursorLoader(context, contentUri, proj, null, null, null)
        val cursor: Cursor = loader.loadInBackground()!!
        val column_index: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val result: String = cursor.getString(column_index)
        cursor.close()
        return result
    }

    override suspend fun uploadImage(imageUri: Uri, context : Context): Response<FileUploadRes> {
        val imagePath = getFilePathFromUri(imageUri, context)
        val image = File(imagePath)

        val body : RequestBody = RequestBody.create(
            "multipart/form-data".toMediaTypeOrNull(),
            image,
        )
        val fileToUpload : MultipartBody.Part =
            MultipartBody.Part.createFormData("file", image.name, body)

        return fileService.fileUpload(fileToUpload)
    }
}