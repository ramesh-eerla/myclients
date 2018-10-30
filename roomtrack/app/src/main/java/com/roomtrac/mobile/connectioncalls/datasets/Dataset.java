package com.roomtrac.mobile.connectioncalls.datasets;

public class Dataset {
	String stateId,Value,Text,Selected,stateText,suffixId, suffixText,recruiterId, recruiterText,securityClearanceId,securityClearanceText,fileName,filePath,exptitle;
String PreapprovalId,PreapprovalText,currencyId,currencyText,chargeId,chargeText,expenseStatusId,expenseStatusText,expNumber,expAmount,cwNumber,expStatus,expenseReportId,expReportStatusID,expPreApprovalNumber;
int exptitle_id;
	long file_size;
String key,label,controlType,defaultvalue,inputtype,isEditable,isMandatory,display,Id="",text="",ChragenumberDropdownID,ChragenumberDropdownvalue,ChragenumberDropdownselectedValue,ChragenumberDropdownparentFieldID,ChragenumberDropdownfieldName,
ChragenumberDropdownhierarchyFieldValue,ChragenumberDropdownhierarchyFieldTextValue,ChragenumberDropdownparentFieldName;


	String SkillName;
	String SkillDescription;
	String SkillLevelId;
	String SkillYearsExperience;
	String SkillRequired;

	public String getSkillName() {
		return SkillName;
	}

	public void setSkillName(String skillName) {
		SkillName = skillName;
	}

	public String getSkillDescription() {
		return SkillDescription;
	}

	public void setSkillDescription(String skillDescription) {
		SkillDescription = skillDescription;
	}

	public String getSkillLevelId() {
		return SkillLevelId;
	}

	public void setSkillLevelId(String skillLevelId) {
		SkillLevelId = skillLevelId;
	}

	public String getSkillYearsExperience() {
		return SkillYearsExperience;
	}

	public void setSkillYearsExperience(String skillYearsExperience) {
		SkillYearsExperience = skillYearsExperience;
	}

	public String getSkillRequired() {
		return SkillRequired;
	}

	public void setSkillRequired(String skillRequired) {
		SkillRequired = skillRequired;
	}



public void setkey(String key){this.key=key;}
public void setlabel(String label){this.label=label;}
public void setcontrolType(String controlType){this.controlType=controlType;}
public void setdefaultvalue(String defaultvalue){this.defaultvalue=defaultvalue;}
public void setinputtype(String inputtype){this.inputtype=inputtype;}
public void setisEditable(String isEditable){this.isEditable=isEditable;}
public void setisMandatory(String isMandatory){this.isMandatory=isMandatory;}
public void setdisplay(String display){this.display=display;}

public String getkey(){return key;}
public String getlabel(){return label;}
public String getcontrolType(){return controlType;}
public String getdefaultvalue(){return defaultvalue;}
public String getinputtype(){return inputtype;}
public String getisEditable(){return isEditable;}
public String getisMandatory(){return isMandatory;}
public String getdisplay(){return display;}
public void setChragenumberDropdownID(String ChragenumberDropdownID){this.ChragenumberDropdownID=ChragenumberDropdownID;}
public void setChragenumberDropdownvalue(String ChragenumberDropdownvalue){this.ChragenumberDropdownvalue=ChragenumberDropdownvalue;}
public void setChragenumberDropdownselectedValue(String ChragenumberDropdownselectedValue){this.ChragenumberDropdownselectedValue=ChragenumberDropdownselectedValue;}
public void setChragenumberDropdownparentFieldID(String ChragenumberDropdownparentFieldID){this.ChragenumberDropdownparentFieldID=ChragenumberDropdownparentFieldID;}
public void setChragenumberDropdownfieldName(String ChragenumberDropdownfieldName){this.ChragenumberDropdownfieldName=ChragenumberDropdownfieldName;}
public void setChragenumberDropdownhierarchyFieldValue(String ChragenumberDropdownhierarchyFieldValue){this.ChragenumberDropdownhierarchyFieldValue=ChragenumberDropdownhierarchyFieldValue;}
public void setChragenumberDropdownhierarchyFieldTextValue(String ChragenumberDropdownhierarchyFieldTextValue){this.ChragenumberDropdownhierarchyFieldTextValue=ChragenumberDropdownhierarchyFieldTextValue;}
public void setChragenumberDropdownparentFieldName(String ChragenumberDropdownparentFieldName){this.ChragenumberDropdownparentFieldName=ChragenumberDropdownparentFieldName;}

public String getChragenumberDropdownID(){
	return ChragenumberDropdownID;
}
public String getChragenumberDropdownvalue(){return ChragenumberDropdownvalue;}
public String getChragenumberDropdownselectedValue(){return ChragenumberDropdownselectedValue;}
public String getChragenumberDropdownparentFieldID(){return ChragenumberDropdownparentFieldID;}
public String getChragenumberDropdownfieldName(){return ChragenumberDropdownfieldName;}
public String getChragenumberDropdownhierarchyFieldValue(){return ChragenumberDropdownhierarchyFieldValue;}
public String getChragenumberDropdownhierarchyFieldTextValue(){return ChragenumberDropdownhierarchyFieldTextValue;}
public String getChragenumberDropdownparentFieldName(){return ChragenumberDropdownparentFieldName;}

	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}

	public long getFile_size() {
		return file_size;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getStateId() {
		return stateId;
	}

	public void setId(String Id) {
		this.Id = Id;
	}

	public String getId() {
		return Id;
	}
	public void setTextt(String text) {
		this.text = text;
	}

	public String geTextt() {
		return text;
	}

	public void setStateName(String stateText) {
		this.stateText = stateText;
	}

	public String getStateName() {
		return stateText;
	}
	public void setSuffixId(String suffixId) {
		this.suffixId = suffixId;
	}

	public String getSuffixId() {
		return suffixId;
	}

	public void setSuffixName(String suffixText) {
		this.suffixText = suffixText;
	}

	public String getSuffixName() {
		return suffixText;
	}
	public void setRecruiterId(String recruiterId) {
		this.recruiterId = recruiterId;
	}

	public String getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterName(String recruiterText) {
		this.recruiterText = recruiterText;
	}

	public String getRecruiterName() {
		return recruiterText;
	}
	
	public void setSecurityClearanceId(String securityClearanceId) {
		this.securityClearanceId = securityClearanceId;
	}

	public String getSecurityClearanceId() {
		return securityClearanceId;
	}
	public void setsecurityClearanceText(String securityClearanceText) {
		this.securityClearanceText = securityClearanceText;
	}

	public String getSecurityClearanceText() {
		return securityClearanceText;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
	public String getCurrencyId() {
		return currencyId;
	}
	public void setChargeText(String chargeText) {
		this.chargeText = chargeText;
	}
	public String getChargeText() {
		return chargeText;
	}
	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}
	public String getChargeId() {
		return chargeId;
	}
	public void setCurrencyText(String currencyText) {
		this.currencyText = currencyText;
	}
	public String getCurrencyText() {
		return currencyText;
	}
	/*****************expense*************************************************/
	public void setexpenseStatusId(String expenseStatusId) {
		this.expenseStatusId=expenseStatusId;
	}
	public String getexpenseStatusId() {
		return expenseStatusId;
	}
	public void setexpenseStatusText(String expenseStatusText) {
		this.expenseStatusText=expenseStatusText;
	}
	public String getexpenseStatusText() {
		return expenseStatusText;
	}
	
	public void setexpNumber(String expNumber) {
		this.expNumber=expNumber;
	}
	public String getexpNumber() {
		return expNumber;
	}
	
	public void setexpAmount(String expAmount) {
		this.expAmount=expAmount;
	}
	public String getexpAmount() {
		return expAmount;
	}
	
	public void setcwNumber(String cwNumber) {
		this.cwNumber=cwNumber;
	}
	public String getcwNumber() {
		return cwNumber;
	}
	
	public void setexpStatus(String expStatus) {
		this.expStatus=expStatus;
	}
	public String getexpStatus() {
		return expStatus;
	}
	
	public void setexpenseReportId(String expenseReportId) {
		this.expenseReportId=expenseReportId;
	}
	public String getexpenseReportId() {
		return expenseReportId;
	}
	
	public void setexpReportStatusID(String expReportStatusID) {
		this.expReportStatusID=expReportStatusID;
	}
	public String getexpReportStatusID() {
		return expReportStatusID;
	}
	
	public void setexpPreApprovalNumber(String expPreApprovalNumber) {
		this.expPreApprovalNumber=expPreApprovalNumber;
	}
	public String getexpPreApprovalNumber() {
		return expPreApprovalNumber;
	}

	public void setexptitle(String exp_title) {
		this.exptitle=exp_title;
		
	}
public String getexptitle() {
	return exptitle;
		
	}

	public void setexptitle_id(int exptitle_id) {
		this.exptitle_id=exptitle_id;
	}
public int getexptitle_id() {
		return exptitle_id;
	}

public void setPreApprovalID(String PreapprovalId) {
	this.PreapprovalId=PreapprovalId;
	
}
public String getPreApprovalID() {
	return PreapprovalId;
		
	}

public void setText(String Text) {
	this.Text=Text;
	
}
public String getText() {
	return Text;
		
	}

public void setSelected(String Selected) {
	this.Selected=Selected;
	
}
public String getSelected() {
	return Selected;
		
	}

public void setValue(String Value) {
	this.Value=Value;
	
}
public String getValue() {
	return Value;
		
	}

public void setPreApprovalText(String PreapprovalText) {
	this.PreapprovalText=PreapprovalText;
	
}
public String getPreApprovalText() {
	return PreapprovalText;
		
	}
}
