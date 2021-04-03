package edu.utn.mailapi.domain;

public enum RecipientType {
    TO("TO"),
    CC("CC"),
    BCC("BCC");

    String description;

    RecipientType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RecipientType{" +
                "description='" + description + '\'' +
                '}';
    }
}
