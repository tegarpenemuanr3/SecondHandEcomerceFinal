package com.tegarpenemuan.secondhandecomerce.repository

import com.example.projectgroup2.data.api.main.updateproduct.UpdateProductResponse
import com.tegarpenemuan.secondhandecomerce.data.api.Api
import com.tegarpenemuan.secondhandecomerce.data.api.BuyerOrder.CreateOrder.createOrderRequest
import com.tegarpenemuan.secondhandecomerce.data.api.BuyerOrder.CreateOrder.createOrderResponse
import com.tegarpenemuan.secondhandecomerce.data.api.BuyerOrder.GetDetailOrder.GetDetailOrderResponse
import com.tegarpenemuan.secondhandecomerce.data.api.BuyerOrder.UpdateStatusOrder.UpdateStatusOrderRequest
import com.tegarpenemuan.secondhandecomerce.data.api.BuyerOrder.UpdateStatusOrder.UpdateStatusOrderResponse
import com.tegarpenemuan.secondhandecomerce.data.api.Notification.GetDetail.GetDetailNotifResponse
import com.tegarpenemuan.secondhandecomerce.data.api.Notification.GetNotification.GetNotifResponseItem
import com.tegarpenemuan.secondhandecomerce.data.api.Notification.updateRead.UpdateReadResponse
import com.tegarpenemuan.secondhandecomerce.data.api.category.GetCategoryResponseItem
import com.tegarpenemuan.secondhandecomerce.data.api.Product.GetProductResponse
import com.tegarpenemuan.secondhandecomerce.data.api.ProductDetail.GetProductDetailsResponse
import com.tegarpenemuan.secondhandecomerce.data.api.SellerOrder.SellerOrderResponseItem
import com.tegarpenemuan.secondhandecomerce.data.api.banner.BannerResponseItem
import com.tegarpenemuan.secondhandecomerce.data.api.deleteproduct.DeleteSellerProductResponse
import com.tegarpenemuan.secondhandecomerce.data.api.detailproduct.DetailProductResponse
import com.tegarpenemuan.secondhandecomerce.data.api.getProfile.GetProfileResponse
import com.tegarpenemuan.secondhandecomerce.data.api.login.LoginRequest
import com.tegarpenemuan.secondhandecomerce.data.api.login.LoginResponse
import com.tegarpenemuan.secondhandecomerce.data.api.register.request.SignUpRequest
import com.tegarpenemuan.secondhandecomerce.data.api.register.response.SuccessRegisterResponse
import com.tegarpenemuan.secondhandecomerce.data.api.updateUser.UpdateUserRequest
import com.tegarpenemuan.secondhandecomerce.data.api.updateUser.UpdateUserResponse
import com.tegarpenemuan.secondhandecomerce.datastore.AuthDatastoreManager
import kotlinx.coroutines.flow.firstOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val authDatastore: AuthDatastoreManager,
    private val api: Api,
) {
    suspend fun clearToken() {
        updateToken("")
    }

    suspend fun clearID() {
        setId("")
    }

    suspend fun updateToken(value: String) {
        authDatastore.setToken(value)
    }

    suspend fun getToken(): String? {
        return authDatastore.getToken().firstOrNull()
    }

    suspend fun setId(value: String) {
        authDatastore.setID(value)
    }

    suspend fun getId(): String? {
        return authDatastore.getId().firstOrNull()
    }

    suspend fun register(request: SignUpRequest): Response<SuccessRegisterResponse> {
        return api.register(
            full_name = request.full_name,
            email = request.email,
            password = request.password,
            phone_number = request.phone_number,
            address = request.address,
            image = request.image,
            city = request.city
        )
    }

    suspend fun login(request: LoginRequest): Response<LoginResponse> {
        return api.login(request)
    }

    suspend fun getProfile(access_token: String): Response<GetProfileResponse> {
        return api.getProfile(access_token = access_token)
    }

    suspend fun getProduct(
        status: String?,
        category_id: Int?,
        search: String?
    ): Response<List<GetProductResponse>> {
        return api.getProduct(status, category_id, search)
    }

    suspend fun getOrder(
        access_token: String,
        status: String?
    ): Response<List<SellerOrderResponseItem>> {
        return api.getOrder(access_token, status)
    }

    suspend fun getDetailOrder(
        access_token: String,
        id: Int
    ): Response<GetDetailOrderResponse> {
        return api.getDetailOrder(access_token, id)
    }

    suspend fun updateStatusOrder(
        access_token: String,
        id: Int,
        request: UpdateStatusOrderRequest
    ): Response<UpdateStatusOrderResponse> {
        return api.updateStatusOrder(access_token, id, request)
    }

    suspend fun getCategory(): Response<List<GetCategoryResponseItem>> {
        return api.getCategory()
    }

    suspend fun getNotification(access_token: String): Response<List<GetNotifResponseItem>> {
        return api.getNotification(access_token)
    }

    suspend fun getDetailNotification(
        id: Int,
        access_token: String
    ): Response<GetDetailNotifResponse> {
        return api.getDetailNotification(id, access_token)
    }

    suspend fun updateReadNotif(id: Int, access_token: String): Response<UpdateReadResponse> {
        return api.updateReadNotif(id, access_token)
    }


    suspend fun createOrder(
        access_token: String,
        request: createOrderRequest
    ): Response<createOrderResponse> {
        return api.createOrder(access_token, request)
    }

    suspend fun updateUser(
        access_token: String,
        request: UpdateUserRequest
    ): Response<UpdateUserResponse> {
        return api.updateUser(
            access_token = access_token,
            full_name = request.full_name,
            phone_number = request.phone_number,
            address = request.address,
            image = request.image,
            city = request.city
        )
    }

    suspend fun getProductId(
        id: Int
    ): Response<GetProductDetailsResponse> {
        return api.getProductDetails(
            id = id
        )
    }

    suspend fun getProductSeller(access_token: String): Response<List<GetProductResponse>> {
        return api.getProduct(access_token)
    }

    suspend fun uploadProductSeller(
        token: String,
        file: MultipartBody.Part,
        name: RequestBody,
        description: RequestBody,
        base_price: RequestBody,
        categoryIds: List<Int>,
        location: RequestBody,
    ): Response<GetProductResponse> {
        return api.addProduct(token, file, name, description, base_price, categoryIds, location)
    }

    suspend fun updateProduct(
        token: String,
        id: Int,
        file: MultipartBody.Part?,
        name: RequestBody,
        description: RequestBody,
        base_price: RequestBody,
        categoryIds: List<Int>,
        location: RequestBody,
    ): Response<UpdateProductResponse> {
        return api.updateProduct(
            token,
            id,
            file,
            name,
            description,
            base_price,
            categoryIds,
            location
        )
    }

    suspend fun deleteSellerProduct(token: String, id: Int): Response<DeleteSellerProductResponse> {
        return api.deleteSellerProduct(token = token, id = id)
    }

    suspend fun getProductId(token: String, id: Int): Response<DetailProductResponse> {
        return api.getProductId(access_token = token, id = id)
    }


    suspend fun getBanner(): Response<List<BannerResponseItem>> {
        return api.getBanner()
    }
}