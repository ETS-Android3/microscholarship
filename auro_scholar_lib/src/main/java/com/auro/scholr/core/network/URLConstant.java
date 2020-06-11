package com.auro.scholr.core.network;


public interface URLConstant {

    String BASE_URL = "httpS://auroscholar.com/api/"; // PRODUCTION
    // String BASE_URL = "http://3.210.192.55:6060/"; // UAT
//    String BASE_URL = "http://14.142.204.99:7070/"; // SIT
    // String BASE_URL = BuildConfig.BASE_URL;

    String DASHBOARD_API = BASE_URL + "dashboard.php";

    String DASHBOARD_SDK_API = BASE_URL + "dashboard_sdk.php";

    String DEMOGRAPHIC_API = BASE_URL + "demographics.php";

    String AZURE_API = BASE_URL + "faceimg.php";


    String UPLOAD_IMAGE_URL = BASE_URL + "kyc.php";
    String GET_ASSIGNMENT_ID = BASE_URL + "statrquizeaction.php ";
    String TEST_URL = "https://assessment.eklavvya.com/exam/StartExam?";
    String PRIVACY_POLICY = "https://auroscholar.com/privacy_policy.php";

    String TEACHER_PROFILE_UPDATE_API = BASE_URL + "teacher_profile_data_api.php ";

    String GET_PROFILE_UPDATE_API = BASE_URL + "students_score_data_api.php ";

    String GET_PROFILE_TEACHER_API = BASE_URL + "teacher_profile_display_api.php ";

    String TEACHER_KYC_API = BASE_URL + "teacher_kyc_api.php ";

    String INVITE_FRIEND_LIST_API = BASE_URL + "student_referral_data.php";


}
