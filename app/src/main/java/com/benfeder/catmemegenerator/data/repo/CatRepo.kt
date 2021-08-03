package com.benfeder.catmemegenerator.data.repo

import com.benfeder.catmemegenerator.data.models.Cat
import com.benfeder.catmemegenerator.data.remote.CatManager
import com.benfeder.catmemegenerator.utils.Resource

import java.lang.Exception
import javax.inject.Inject


class CatRepo @Inject constructor(
    private val catManager: CatManager
){

    suspend fun getCatImage(filter: String?) : Resource<Cat> {
        return try {
            val catResponse = catManager.getCatImage(filter)
            if (catResponse.isSuccessful && catResponse.body() != null) {
                Resource.Success(catResponse.body()!!)
            } else {
                Resource.Error(null, "No animal found")
            }
        } catch (ex : Exception) {
            Resource.Error(ex, "Unexpected Error")
        }
    }

    suspend fun getCatImageWithText(filter: String?, text: String, color: String?, size: Int?) : Resource<Cat> {
        return try {
            val catResponse = catManager.getCatImageWithText(filter, text, color, size)
            if (catResponse.isSuccessful && catResponse.body() != null) {
                Resource.Success(catResponse.body()!!)
            } else {
                Resource.Error(null, "No animal found")
            }
        } catch (ex : Exception) {
            Resource.Error(ex, "Unexpected Error")
        }
    }

    suspend fun getCatGif(filter: String?) : Resource<Cat> {
        return try {
            val catResponse = catManager.getCatGif(filter)
            if (catResponse.isSuccessful && catResponse.body() != null) {
                Resource.Success(catResponse.body()!!)
            } else {
                Resource.Error(null, "No animal found")
            }
        } catch (ex : Exception) {
            Resource.Error(ex, "Unexpected Error")
        }
    }
    suspend fun getCatGifWithText(filter: String?, text: String, color: String?, size: Int?) : Resource<Cat> {
        return try {
            val catResponse = catManager.getCatGifWithText(filter, text, color, size)
            if (catResponse.isSuccessful && catResponse.body() != null) {
                Resource.Success(catResponse.body()!!)
            } else {
                Resource.Error(null, "No animal found")
            }
        } catch (ex : Exception) {
            Resource.Error(ex, "Unexpected Error")
        }
    }
}