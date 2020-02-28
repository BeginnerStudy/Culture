package com.practice.culture;

public class Antiquity {

    private String CaseID;
    private String CaseName;
    private String AssetsTypeName;
    private String PastHistory;
    private String GovInstitutionName;
    private String BelongAddress;
    private String BelongCity;
    private String Longitude;
    private String Latitude;
    private String CaseUrl;
    private String BuildingFeatures;
    private String InHouseFeatures;
    private String BuildingActualState;
    private String BuildingUsage;
    private String BuildingKeyMaintainItem;
    private String RepresentImage;

    public Antiquity(String caseID, String caseName, String assetsTypeName, String pastHistory, String govInstitutionName,
                     String belongAddress, String belongCity, String longitude, String latitude, String caseUrl,
                     String buildingFeatures, String inHouseFeatures, String buildingActualState, String buildingUsage,
                     String buildingKeyMaintainItem, String representImage) {
        CaseID = caseID;
        CaseName = caseName;
        AssetsTypeName = assetsTypeName;
        PastHistory = pastHistory;
        GovInstitutionName = govInstitutionName;
        BelongAddress = belongAddress;
        BelongCity = belongCity;
        Longitude = longitude;
        Latitude = latitude;
        CaseUrl = caseUrl;
        BuildingFeatures = buildingFeatures;
        InHouseFeatures = inHouseFeatures;
        BuildingActualState = buildingActualState;
        BuildingUsage = buildingUsage;
        BuildingKeyMaintainItem = buildingKeyMaintainItem;
        RepresentImage = representImage;
    }

    public String getCaseID() {
        return CaseID;
    }

    public void setCaseID(String caseID) {
        CaseID = caseID;
    }

    public String getCaseName() {
        return CaseName;
    }

    public void setCaseName(String caseName) {
        CaseName = caseName;
    }

    public String getAssetsTypeName() {
        return AssetsTypeName;
    }

    public void setAssetsTypeName(String assetsTypeName) {
        AssetsTypeName = assetsTypeName;
    }

    public String getPastHistory() {
        return PastHistory;
    }

    public void setPastHistory(String pastHistory) {
        PastHistory = pastHistory;
    }

    public String getGovInstitutionName() {
        return GovInstitutionName;
    }

    public void setGovInstitutionName(String govInstitutionName) {
        GovInstitutionName = govInstitutionName;
    }

    public String getBelongAddress() {
        return BelongAddress;
    }

    public void setBelongAddress(String belongAddress) {
        BelongAddress = belongAddress;
    }

    public String getBelongCity() {
        return BelongCity;
    }

    public void setBelongCity(String belongCity) {
        BelongCity = belongCity;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getCaseUrl() {
        return CaseUrl;
    }

    public void setCaseUrl(String caseUrl) {
        CaseUrl = caseUrl;
    }

    public String getBuildingFeatures() {
        return BuildingFeatures;
    }

    public void setBuildingFeatures(String buildingFeatures) {
        BuildingFeatures = buildingFeatures;
    }

    public String getInHouseFeatures() {
        return InHouseFeatures;
    }

    public void setInHouseFeatures(String inHouseFeatures) {
        InHouseFeatures = inHouseFeatures;
    }

    public String getBuildingActualState() {
        return BuildingActualState;
    }

    public void setBuildingActualState(String buildingActualState) {
        BuildingActualState = buildingActualState;
    }

    public String getBuildingUsage() {
        return BuildingUsage;
    }

    public void setBuildingUsage(String buildingUsage) {
        BuildingUsage = buildingUsage;
    }

    public String getBuildingKeyMaintainItem() {
        return BuildingKeyMaintainItem;
    }

    public void setBuildingKeyMaintainItem(String buildingKeyMaintainItem) {
        BuildingKeyMaintainItem = buildingKeyMaintainItem;
    }

    public String getRepresentImage() {
        return RepresentImage;
    }

    public void setRepresentImage(String representImage) {
        RepresentImage = representImage;
    }
}