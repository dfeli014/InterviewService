package com.revature.dtos;

import java.util.Date;
import java.util.Objects;

import com.revature.models.InterviewFormat;

public class NewAssociateInput {
    private int interviewId;
    private int associateInputId;
    private Date receivedNotifications;
    private boolean descriptionProvided;
    private InterviewFormat interviewFormat;
    private InterviewFormat proposedFormat;

    public NewAssociateInput() {
        super();
    }

    public NewAssociateInput(int interviewId, int associateInputId, Date receivedNotifications,
            boolean descriptionProvided, InterviewFormat interviewFormat, InterviewFormat proposedFormat) {
        this.interviewId = interviewId;
        this.associateInputId = associateInputId;
        this.receivedNotifications = receivedNotifications;
        this.descriptionProvided = descriptionProvided;
        this.interviewFormat = interviewFormat;
        this.proposedFormat = proposedFormat;
    }

    public int getInterviewId() {
        return this.interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public int getAssociateInputId() {
        return this.associateInputId;
    }

    public void setAssociateInputId(int associateInputId) {
        this.associateInputId = associateInputId;
    }

    public Date getReceivedNotifications() {
        return this.receivedNotifications;
    }

    public void setReceivedNotifications(Date receivedNotifications) {
        this.receivedNotifications = receivedNotifications;
    }

    public boolean isDescriptionProvided() {
        return this.descriptionProvided;
    }

    public boolean getDescriptionProvided() {
        return this.descriptionProvided;
    }

    public void setDescriptionProvided(boolean descriptionProvided) {
        this.descriptionProvided = descriptionProvided;
    }

    public InterviewFormat getInterviewFormat() {
        return this.interviewFormat;
    }

    public void setInterviewFormat(InterviewFormat interviewFormat) {
        this.interviewFormat = interviewFormat;
    }

    public InterviewFormat getProposedFormat() {
        return this.proposedFormat;
    }

    public void setProposedFormat(InterviewFormat proposedFormat) {
        this.proposedFormat = proposedFormat;
    }

    public NewAssociateInput interviewId(int interviewId) {
        this.interviewId = interviewId;
        return this;
    }

    public NewAssociateInput associateInputId(int associateInputId) {
        this.associateInputId = associateInputId;
        return this;
    }

    public NewAssociateInput receivedNotifications(Date receivedNotifications) {
        this.receivedNotifications = receivedNotifications;
        return this;
    }

    public NewAssociateInput descriptionProvided(boolean descriptionProvided) {
        this.descriptionProvided = descriptionProvided;
        return this;
    }

    public NewAssociateInput interviewFormat(InterviewFormat interviewFormat) {
        this.interviewFormat = interviewFormat;
        return this;
    }

    public NewAssociateInput proposedFormat(InterviewFormat proposedFormat) {
        this.proposedFormat = proposedFormat;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof NewAssociateInput)) {
            return false;
        }
        NewAssociateInput newAssociateInput = (NewAssociateInput) o;
        return interviewId == newAssociateInput.interviewId && associateInputId == newAssociateInput.associateInputId
                && Objects.equals(receivedNotifications, newAssociateInput.receivedNotifications)
                && descriptionProvided == newAssociateInput.descriptionProvided
                && Objects.equals(interviewFormat, newAssociateInput.interviewFormat)
                && Objects.equals(proposedFormat, newAssociateInput.proposedFormat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interviewId, associateInputId, receivedNotifications, descriptionProvided, interviewFormat,
                proposedFormat);
    }

    @Override
    public String toString() {
        return "{" + " interviewId='" + getInterviewId() + "'" + ", associateInputId='" + getAssociateInputId() + "'"
                + ", receivedNotifications='" + getReceivedNotifications() + "'" + ", descriptionProvided='"
                + isDescriptionProvided() + "'" + ", interviewFormat='" + getInterviewFormat() + "'"
                + ", proposedFormat='" + getProposedFormat() + "'" + "}";
    }

}