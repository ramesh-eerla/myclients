package com.roomtrac.mobile.connectioncalls.datasets;

/**
 * Created by santosh.baratam on 11/10/2017.
 */

/*------------------------------------------------------------
        Developer : Santosh Kumar Baratam
        Use :: To store the data
        Class Purpose: This class is used to create a request params model for Retrofit calls, By default we are not assigning any values and will be added based on implementation
        ------------------------------------------------------------*/
public class BasicRequestParams {
    public Integer mspId, clientId, proxyForUserId, userId, requirementID, candidateID, userType, candActionID, supplierId,expenseReportId,cwDetailId;
    public String sessionKey, language, fullName, comments, requirementId,keywords,CountryCode,StateCode,ZipCode,IDNum,text,Language,Country,Code,DesktopUserId,deptID;
    public Boolean SubmitToCustomerFlag;
    public CandidateWithdrawEntity candWithdrawEntity,expensereportentity,expenseReportNotes,rejectOfferEntity,declineReqEntity;

    public static class CandidateWithdrawEntity {
        public String candidateName, CLSRNumber, Comment, WithdrawReasonID, candActionID, CandActionTypeID, CandStatusID,RejectOfferReasonID,candActionTypeID,Comments,conversionRate;
        public Integer ReqID, CandidateID,saeID,ForeignCurrencyID,declineReasonId;
        public String cashAdvAmount,dateTo, cwNumber, expenseReportID, purpose, dateFrom, title,currencyId,chargeNumber,notes,ForeignCurrencyCode,deptID;
    }
}
