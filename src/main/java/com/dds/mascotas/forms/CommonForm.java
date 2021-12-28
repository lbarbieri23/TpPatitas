package com.dds.mascotas.forms;

public abstract class CommonForm {

    private String toastMessage;
    private String mode;

    public Boolean getIsToastShow() {
        return toastMessage != null && !"".equals(toastMessage);
    }

    public String getToastMessage() {
        String result = toastMessage;
        toastMessage = null;
        return result;
    }

    public void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
