
package com.roomtrack.mobile.connectioncalls.datasets;
/**
 * Created by Ramesh.eerla on 24/10/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteResumeDataset {
    @SerializedName("IsDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("DeleteResumeSaveFileId")
    @Expose
    private Integer deleteResumeSaveFileId;
    @SerializedName("AdditionalDocDeleteSaveId")
    @Expose
    private Integer additionalDocDeleteSaveId;

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getDeleteResumeSaveFileId() {
        return deleteResumeSaveFileId;
    }

    public void setDeleteResumeSaveFileId(Integer deleteResumeSaveFileId) {
        this.deleteResumeSaveFileId = deleteResumeSaveFileId;
    }

    public Integer getAdditionalDocDeleteSaveId() {
        return additionalDocDeleteSaveId;
    }

    public void setAdditionalDocDeleteSaveId(Integer additionalDocDeleteSaveId) {
        this.additionalDocDeleteSaveId = additionalDocDeleteSaveId;
    }

}
