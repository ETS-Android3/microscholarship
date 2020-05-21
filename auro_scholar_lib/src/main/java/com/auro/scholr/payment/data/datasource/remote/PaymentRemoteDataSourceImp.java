package com.auro.scholr.payment.data.datasource.remote;

import com.auro.scholr.core.common.AppConstant;
import com.auro.scholr.home.data.datasource.remote.HomeRemoteApi;
import com.auro.scholr.home.data.model.AuroScholarDataModel;
import com.auro.scholr.payment.data.repository.PaymentRepo;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.Response;

public class PaymentRemoteDataSourceImp implements PaymentRepo.PaymentRemoteData {

    PaymentRemoteApi paymentRemoteApi;

    public PaymentRemoteDataSourceImp(PaymentRemoteApi paymentRemoteApi) {
        this.paymentRemoteApi = paymentRemoteApi;
    }


    @Override
    public Single<Response<JsonObject>> getDashboardData(AuroScholarDataModel model) {
        /*These param for generic sdk*/
        Map<String, String> params = new HashMap<String, String>();
        params.put(AppConstant.MOBILE_NUMBER, model.getMobileNumber());
        params.put(AppConstant.DashBoardParams.STUDENT_CLASS, model.getStudentClass());
        params.put(AppConstant.DashBoardParams.REGISTRATION_SOURCE, model.getRegitrationSource());
        //params.put(AppConstant.DashBoardParams.SCHOLAR_ID, model.getScholrId());
        //params.put(AppConstant.DashBoardParams.SHARE_TYPE, model.getShareType());
        //params.put(AppConstant.DashBoardParams.SHARE_IDENTITY, model.getShareIdentity());
        return paymentRemoteApi.getDashboardData(params);
    }


}
