package com.roomtrack.mobile.connectioncalls.datasets;\
/**
 * Created by Ramesh.eerla on 24/10/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResumeAndDocumentUploadDataSet {

    @SerializedName("Success")
    @Expose
    private Boolean success;
    @SerializedName("IsUploaded")
    @Expose
    private Boolean isUploaded;
    @SerializedName("FileName")
    @Expose
    private String fileName;
    @SerializedName("ResumeFileSaveID")
    @Expose
    private Integer resumeFileSaveID;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Value")
    @Expose
    private Object value;
    @SerializedName("OriginalFileName")
    @Expose
    private Object originalFileName;
    @SerializedName("UniqueID")
    @Expose
    private String uniqueID;
    @SerializedName("POSaveId")
    @Expose
    private Integer pOSaveId;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getIsUploaded() {
        return isUploaded;
    }

    public void setIsUploaded(Boolean isUploaded) {
        this.isUploaded = isUploaded;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getResumeFileSaveID() {
        return resumeFileSaveID;
    }

    public void setResumeFileSaveID(Integer resumeFileSaveID) {
        this.resumeFileSaveID = resumeFileSaveID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(Object originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public Integer getPOSaveId() {
        return pOSaveId;
    }

    public void setPOSaveId(Integer pOSaveId) {
        this.pOSaveId = pOSaveId;
    }

}
