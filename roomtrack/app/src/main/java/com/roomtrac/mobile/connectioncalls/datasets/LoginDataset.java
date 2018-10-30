package com.roomtrac.mobile.connectioncalls.datasets;

/**
 * Created by Ramesh.eerla on 24/10/2018.
 */
import java.io.Serializable;
import java.util.List;


public class LoginDataset implements Serializable{
public class AlertsEntity implements Serializable{

    private String ConfirmationAlert;
    private String WarningAlert;
    private String ErrorAlert;

    public String getConfirmationAlert() {
        return ConfirmationAlert;
    }

    public void setConfirmationAlert(String ConfirmationAlert) {
        this.ConfirmationAlert = ConfirmationAlert;
    }

    public String getWarningAlert() {
        return WarningAlert;
    }

    public void setWarningAlert(String WarningAlert) {
        this.WarningAlert = WarningAlert;
    }

    public String getErrorAlert() {
        return ErrorAlert;
    }

    public void setErrorAlert(String ErrorAlert) {
        this.ErrorAlert = ErrorAlert;
    }

}


    public class MspClientList implements Serializable{

        private Integer _MspClientID;
        private String _ClientName;
        private Integer _ClientID;

        public Integer getMspClientID() {
            return _MspClientID;
        }

        public void setMspClientID(Integer _MspClientID) {
            this._MspClientID = _MspClientID;
        }

        public String getClientName() {
            return _ClientName;
        }

        public void setClientName(String _ClientName) {
            this._ClientName = _ClientName;
        }

        public Integer getClientID() {
            return _ClientID;
        }

        public void setClientID(Integer _ClientID) {
            this._ClientID = _ClientID;
        }

    }

    public class MspClientSupplierList implements Serializable{



        private Integer _mspID;
        private Integer _clientID;
        private String _Name;
        private Integer _MSPClientSupplierID;

        public Integer getmspID() {
            return _mspID;
        }

        public void setmspID(Integer _mspID) {
            this._mspID = _mspID;
        }

        public Integer getclientID() {
            return _clientID;
        }

        public void setclientID(Integer _clientID) {
            this._clientID = _clientID;
        }

        public String getName() {
            return _Name;
        }

        public void setName(String _Name) {
            this._Name = _Name;
        }

        public Integer getMSPClientSupplierID() {
            return _MSPClientSupplierID;
        }

        public void setMSPClientSupplierID(Integer _MSPClientSupplierID) {
            this._MSPClientSupplierID = _MSPClientSupplierID;
        }

    }

public class ClientMspList implements Serializable{

    private Integer _MspClientID;
    private String _MSPName;
    private Integer _MSPID;

    public Integer getMspClientID() {
        return _MspClientID;
    }

    public void setMspClientID(Integer _MspClientID) {
        this._MspClientID = _MspClientID;
    }

    public String getMSPName() {
        return _MSPName;
    }

    public void setMSPName(String _MSPName) {
        this._MSPName = _MSPName;
    }

    public Integer getMSPID() {
        return _MSPID;
    }

    public void setMSPID(Integer _MSPID) {
        this._MSPID = _MSPID;
    }

}
public class CommonErrorMessages implements Serializable{

    private String NoNetwork;
    private String InvalidResponse;
    private String ConnectionTimeout;
    private String LoginErrorMsg1;
    private String LoginErrorMsg2;
    private String LoginErrorMsg3;
    private String LoginErrorMsg4;
    private String LoginErrorMsg5;
    private String ToDoListValidationMessage;

    public String getToDoListValidationMessage() {
        return ToDoListValidationMessage;
    }

    public void setToDoListValidationMessage(String ToDoListValidationMessage) {
        this.ToDoListValidationMessage = ToDoListValidationMessage;
    }

    public String getNoNetwork() {
        return NoNetwork;
    }

    public void setNoNetwork(String NoNetwork) {
        this.NoNetwork = NoNetwork;
    }

    public String getInvalidResponse() {
        return InvalidResponse;
    }

    public void setInvalidResponse(String InvalidResponse) {
        this.InvalidResponse = InvalidResponse;
    }

    public String getConnectionTimeout() {
        return ConnectionTimeout;
    }

    public void setConnectionTimeout(String ConnectionTimeout) {
        this.ConnectionTimeout = ConnectionTimeout;
    }

    public String getLoginErrorMsg1() {
        return LoginErrorMsg1;
    }

    public void setLoginErrorMsg1(String LoginErrorMsg1) {
        this.LoginErrorMsg1 = LoginErrorMsg1;
    }

    public String getLoginErrorMsg2() {
        return LoginErrorMsg2;
    }

    public void setLoginErrorMsg2(String LoginErrorMsg2) {
        this.LoginErrorMsg2 = LoginErrorMsg2;
    }

    public String getLoginErrorMsg3() {
        return LoginErrorMsg3;
    }

    public void setLoginErrorMsg3(String LoginErrorMsg3) {
        this.LoginErrorMsg3 = LoginErrorMsg3;
    }

    public String getLoginErrorMsg4() {
        return LoginErrorMsg4;
    }

    public void setLoginErrorMsg4(String LoginErrorMsg4) {
        this.LoginErrorMsg4 = LoginErrorMsg4;
    }

    public String getLoginErrorMsg5() {
        return LoginErrorMsg5;
    }

    public void setLoginErrorMsg5(String LoginErrorMsg5) {
        this.LoginErrorMsg5 = LoginErrorMsg5;
    }

}


    private String SessionKey;
    private Boolean Status;
    private String Message;
    private Integer Mspid;
    private Integer ClientID;
    private Integer SupplierId;
    private Integer userID;
    private Integer MspClientID;
    private Integer MspClientSupplierID;
    private String Fullname;
    private String Title;
    private String ClientName;
    private String usertype;
    private List<MspClientList> MspClientList;
    private List<ClientMspList> ClientMspList = null;
    private List<MspClientSupplierList> MspClientSupplierList = null;
    private String CWID;
    private String Loginurlmobile;
    private String Loginurltablet;
    private CommonErrorMessages CommonErrorMessages;
    private AlertsEntity AlertsEntity;
    private List<SettingsEntity> SettingsEntity = null;
    private Integer proxyUserId;
    private Integer DesktopUserId;
    private Integer UserTypeId;
    private List<MobieModuleList> MobieModuleList = null;
    private String ScreenTitle;
    /*for supplier we need to use old session key 
     * Ramesh eerla
     * */
    private String OldSessionKey;
    private String web_api_url;
    private String WCF_url;

    public void setOldSessionKey(String oldSessionKey) {
        OldSessionKey = oldSessionKey;
    }

    public String getOldSessionKey() {
        return OldSessionKey;
    }

    public String getWeb_api_url() {
        return web_api_url;
    }

    public void setWeb_api_url(String web_api_url) {
        this.web_api_url = web_api_url;
    }

    public String getWCF_url() {
        return WCF_url;
    }

    public void setWCF_url(String WCF_url) {
        this.WCF_url = WCF_url;
    }

    public String getSessionKey() {
        return SessionKey;
    }

    public void setSessionKey(String SessionKey) {
        this.SessionKey = SessionKey;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        this.Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        this.Message = message;
    }

    public Integer getMspid() {
        return Mspid;
    }

    public void setMspid(Integer Mspid) {
        this.Mspid = Mspid;
    }

    public Integer getClientID() {
        return ClientID;
    }

    public void setClientID(Integer ClientID) {
        this.ClientID = ClientID;
    }

    public Integer getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(Integer SupplierId) {
        this.SupplierId = SupplierId;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getMspClientID() {
        return MspClientID;
    }

    public void setMspClientID(Integer MspClientID) {
        this.MspClientID = MspClientID;
    }

    public Integer getMspClientSupplierID() {
        return MspClientSupplierID;
    }

    public void setMspClientSupplierID(Integer MspClientSupplierID) {
        this.MspClientSupplierID = MspClientSupplierID;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String ClientName) {
        this.ClientName = ClientName;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public List<MspClientList> getMspClientList() {
        return MspClientList;
    }

    public void setMspClientList(List<MspClientList> MspClientList) {
        this.MspClientList = MspClientList;
    }

    public List<ClientMspList> getClientMspList() {
        return ClientMspList;
    }

    public void setClientMspList(List<ClientMspList> ClientMspList) {
        this.ClientMspList = ClientMspList;
    }

    public List<MspClientSupplierList> getMspClientSupplierList() {
        return MspClientSupplierList;
    }

    public void setMspClientSupplierList(List<MspClientSupplierList> MspClientSupplierList) {
        this.MspClientSupplierList = MspClientSupplierList;
    }

    public String getCWID() {
        return CWID;
    }

    public void setCWID(String CWID) {
        this.CWID = CWID;
    }

    public String getLoginurlmobile() {
        return Loginurlmobile;
    }

    public void setLoginurlmobile(String Loginurlmobile) {
        this.Loginurlmobile = Loginurlmobile;
    }

    public String getLoginurltablet() {
        return Loginurltablet;
    }

    public void setLoginurltablet(String Loginurltablet) {
        this.Loginurltablet = Loginurltablet;
    }

    public CommonErrorMessages getCommonErrorMessages() {
        return CommonErrorMessages;
    }

    public void setCommonErrorMessages(CommonErrorMessages CommonErrorMessages) {
        this.CommonErrorMessages = CommonErrorMessages;
    }

    public AlertsEntity getAlertsEntity() {
        return AlertsEntity;
    }

    public void setAlertsEntity(AlertsEntity AlertsEntity) {
        this.AlertsEntity = AlertsEntity;
    }

    public List<SettingsEntity> getSettingsEntity() {
        return SettingsEntity;
    }

    public void setSettingsEntity(List<SettingsEntity> settingsEntity) {
        this.SettingsEntity = settingsEntity;
    }

    public Integer getProxyUserId() {
        return proxyUserId;
    }

    public void setProxyUserId(Integer proxyUserId) {
        this.proxyUserId = proxyUserId;
    }

    public Integer getDesktopUserId() {
        return DesktopUserId;
    }

    public void setDesktopUserId(Integer DesktopUserId) {
        this.DesktopUserId = DesktopUserId;
    }

    public Integer getUserTypeId() {
        return UserTypeId;
    }

    public void setUserTypeId(Integer UserTypeId) {
        this.UserTypeId = UserTypeId;
    }

    public List<MobieModuleList> getMobieModuleList() {
        return MobieModuleList;
    }

    public void setMobieModuleList(List<MobieModuleList> MobieModuleList) {
        this.MobieModuleList = MobieModuleList;
    }

    public String getScreenTitle() {
        return ScreenTitle;
    }

    public void setScreenTitle(String ScreenTitle) {
        this.ScreenTitle = ScreenTitle;
    }


public class MobieModuleList implements Serializable{

    private String ModuleName;
    private Integer rightID;
    private Integer MobileModuleID;
    private Boolean DefaultModule;
    private Integer Key;

    public String getModuleName() {
        return ModuleName;
    }

    public void setModuleName(String ModuleName) {
        this.ModuleName = ModuleName;
    }

    public Integer getRightID() {
        return rightID;
    }

    public void setRightID(Integer rightID) {
        this.rightID = rightID;
    }

    public Integer getMobileModuleID() {
        return MobileModuleID;
    }

    public void setMobileModuleID(Integer MobileModuleID) {
        this.MobileModuleID = MobileModuleID;
    }

    public Boolean getDefaultModule() {
        return DefaultModule;
    }

    public void setDefaultModule(Boolean DefaultModule) {
        this.DefaultModule = DefaultModule;
    }

    public Integer getKey() {
        return Key;
    }

    public void setKey(Integer Key) {
        this.Key = Key;
    }

}

 public   class SettingsEntity implements Serializable{

    public String Key;
    private String Value;
    private String Messgae;

    public String getKey() {
        return Key;
    }

    public void setKey(String Key) {
        this.Key = Key;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String Value) {
        this.Value = Value;
    }

    public String getMessgae() {
        return Messgae;
    }

    public void setMessgae(String Messgae) {
        this.Messgae = Messgae;
    }

}
}