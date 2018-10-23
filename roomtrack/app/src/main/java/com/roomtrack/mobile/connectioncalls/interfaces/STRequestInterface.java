package com.roomtrack.mobile.connectioncalls.interfaces;

import com.dcrworkforce.mobile.connectioncalls.datasets.AccessToakenSet;
import com.dcrworkforce.mobile.connectioncalls.datasets.DeleteResumeDataset;
import com.dcrworkforce.mobile.connectioncalls.datasets.LoginDataset;
import com.dcrworkforce.mobile.connectioncalls.datasets.ResumeAndDocumentUploadDataSet;
import com.dcrworkforce.mobile.forgetpassword.ForgetPasswordRequestParams;
import com.dcrworkforce.mobile.forgetpassword.ForgetPasswordResponse;
import com.dcrworkforce.mobile.model.AcceptOfferModel;
import com.dcrworkforce.mobile.model.BasicRequestParams;
import com.dcrworkforce.mobile.model.DropdownReasons;
import com.dcrworkforce.mobile.model.KeyValueModel;
import com.dcrworkforce.mobile.model.RetrofitResponse;
import com.dcrworkforce.mobile.requestandresponce.RequestParams;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ramesh.eerla on 1/12/2017.
 */

public interface STRequestInterface {
    /**
     * Created by ramesh.eerla on 1/12/2017.
     *
     * @Path("version") :version number like(v2/mobile)
     * @Path("methodname") : webapi method name
     * @Body : parameters for the request
     */
    @FormUrlEncoded
    @POST("{methodname}")
    Call<AccessToakenSet> getAccessToaken(@Path("methodname") String methodname, @Field("client_id") String client_id, @Field("grant_type") String grant_type, @Field("redirect_uri") String redirect_uri, @Field("client_secret") String client_secret, @Field("scope") String scope);

    @POST("{version}/{methodname}")
    Call<LoginDataset> sso_login(@Path("version") String version, @Path("methodname") String methodname, @Body RequestParams.SSO_Login task);

    @POST("{version}/{methodname}")
    Call<LoginDataset> login(@Path("version") String version, @Path("methodname") String methodname, @Body RequestParams.Logindata task);

   /* for reloaddata no need of any other params
    * only header is fine
    * Ramesh eerla
    * */
    @POST("{version}/{methodname}")
    Call<LoginDataset> reloaddata(@Path("version") String version, @Path("methodname") String methodname);

    @POST("{version}/{methodname}")
    Call<ResumeAndDocumentUploadDataSet> uploadResume(@Path("version") String version, @Path("methodname") String methodname, @Body RequestParams.UploadResumeFile task);

    @POST("{version}/{methodname}")
    Call<ResumeAndDocumentUploadDataSet> uploadAttachment(@Path("version") String version, @Path("methodname") String methodname, @Body RequestParams.UploadAdditionalDocuments task);

    @POST("{version}/{methodname}")
    Call<DeleteResumeDataset> DeleteAttachment(@Path("version") String version, @Path("methodname") String methodname, @Body RequestParams.RemoveAdditionalDocuments task);

    @POST("{version}/{methodname}")
    Call<DeleteResumeDataset> DeleteResume(@Path("version") String version, @Path("methodname") String methodname, @Body RequestParams.DeleteResumeFile task);

    @POST("{version}/{methodname}")
    Call<ForgetPasswordResponse> ForgotPassword(@Path("version") String version, @Path("methodname") String methodname, @Body ForgetPasswordRequestParams.ForgetPasswordParamsForEmailAndSecurityQuestions task);

    @POST("{version}/{methodname}")
    Call<ForgetPasswordResponse> ForgotPasswordQuestionSubmission(@Path("version") String version, @Path("methodname") String methodname, @Body ForgetPasswordRequestParams.ForgetPasswordParamsForSecurityQuestionsSubmission task);


    @POST("{version}/{methodname}")
    Call<RetrofitResponse> retrofitCall(@Path("version") String version, @Path("methodname") String methodname, @Body BasicRequestParams task);

    @POST("{version}/{methodname}")
    Call<AcceptOfferModel> retrofitCallAcceptOffer(@Path("version") String version, @Path("methodname") String methodname, @Body BasicRequestParams task);

    @POST("{version}/{methodname}")
    Call<List<KeyValueModel>> retrofitCallList(@Path("version") String version, @Path("methodname") String methodname, @Body BasicRequestParams task);

    @POST("{version}/{methodname}")
    Call<List<DropdownReasons>> retrofitCallDropdownList(@Path("version") String version, @Path("methodname") String methodname, @Body BasicRequestParams task);

   /* @POST("{version}/{methodname}")
    Call<RetrofitResponse> submitRollbackWithdrawnCandidate(@Path("version") String version, @Path("methodname") String methodname, @Body BasicRequestParams task);

    @POST("{version}/{methodname}")
    Call<RetrofitResponse> fetchRollBackWithDrawnCandidate(@Path("version") String version, @Path("methodname") String methodname, @Body BasicRequestParams task);

    @POST("{version}/{methodname}")
    Call<RetrofitResponse> fetchAndSubmitRequestCandidateResubmissionDetails(@Path("version") String version, @Path("methodname") String methodname, @Body BasicRequestParams task);
*/
}
