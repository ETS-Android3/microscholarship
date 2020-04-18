package com.auro.scholr.home.domain.usecase;

import com.auro.scholr.R;
import com.auro.scholr.core.application.AuroApp;
import com.auro.scholr.core.common.NetworkUtil;
import com.auro.scholr.core.common.ResponseApi;
import com.auro.scholr.core.common.Status;
import com.auro.scholr.core.network.NetworkUseCase;
import com.auro.scholr.home.data.model.DashboardResModel;
import com.auro.scholr.home.data.model.KYCResModel;
import com.auro.scholr.home.data.repository.HomeRepo;
import com.auro.scholr.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import retrofit2.Response;

import static com.auro.scholr.core.common.AppConstant.ResponseConstatnt.RES_200;
import static com.auro.scholr.core.common.AppConstant.ResponseConstatnt.RES_400;
import static com.auro.scholr.core.common.AppConstant.ResponseConstatnt.RES_401;
import static com.auro.scholr.core.common.AppConstant.ResponseConstatnt.RES_FAIL;
import static com.auro.scholr.core.common.Status.DASHBOARD_API;

public class HomeRemoteUseCase extends NetworkUseCase {
    HomeRepo.DashboardRemoteData dashboardRemoteData;
    Gson gson = new Gson();

    public HomeRemoteUseCase(HomeRepo.DashboardRemoteData dashboardRemoteData) {
        this.dashboardRemoteData = dashboardRemoteData;
    }


    public Single<ResponseApi> uploadProfileImage(byte[] imageBytes){

        return dashboardRemoteData.uploadProfileImage(imageBytes).map(new Function<Response<JsonObject>, ResponseApi>() {
            @Override
            public ResponseApi apply(Response<JsonObject> response) throws Exception {

                if (response != null) {

                    return handleResponse(response, Status.UPLOAD_PROFILE_IMAGE);

                } else {

                    return responseFail(null);
                }
            }
        });
    }



    public Single<ResponseApi> getDashboardData(String mobileNo) {

        return dashboardRemoteData.getDashboardData(mobileNo).map(new Function<Response<JsonObject>, ResponseApi>() {
            @Override
            public ResponseApi apply(Response<JsonObject> response) throws Exception {

                if (response != null) {

                    return handleResponse(response, DASHBOARD_API);

                } else {

                    return responseFail(null);
                }
            }
        });
    }

    private ResponseApi handleResponse(Response<JsonObject> response, Status apiTypeStatus) {

        switch (response.code()) {

            case RES_200:
                return response200(response, apiTypeStatus);

            case RES_401:
                return response401(apiTypeStatus);

            case RES_400:
                return responseFail400(response, apiTypeStatus);

            case RES_FAIL:
                return responseFail(apiTypeStatus);

            default:
                return ResponseApi.fail(AuroApp.getAppContext().getString(R.string.default_error), apiTypeStatus);
        }
    }

    @Override
    public Single<Boolean> isAvailInternet() {
        return NetworkUtil.hasInternetConnection();
    }

    @Override
    public ResponseApi response200(Response<JsonObject> response, Status status) {
        if (status == DASHBOARD_API) {

            DashboardResModel dashboardResModel = gson.fromJson(response.body(), DashboardResModel.class);
            return ResponseApi.success(dashboardResModel, status);

        }else if(status == Status.UPLOAD_PROFILE_IMAGE)
        {
            KYCResModel kycResModel = gson.fromJson(response.body(), KYCResModel.class);
            return ResponseApi.success(kycResModel, status);
        }
        return ResponseApi.fail(null, status);
    }

    @Override
    public ResponseApi response401(Status status) {
        return ResponseApi.authFail(401, status);
    }
    @Override
    public ResponseApi responseFail400(Response<JsonObject> response, Status status) {
        try {
            String errorJson = response.errorBody().string();
            String errorMessage = AppUtil.errorMessageHandler(AuroApp.getAppContext().getString(R.string.default_error), errorJson);
            return ResponseApi.fail400(errorMessage, null);
        } catch (Exception e) {
            return ResponseApi.fail(AuroApp.getAppContext().getResources().getString(R.string.default_error), status);
        }
    }


    @Override
    public ResponseApi responseFail(Status status) {
        return ResponseApi.fail(AuroApp.getAppContext().getString(R.string.default_error), status);
    }

}
