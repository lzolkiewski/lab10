package wizut.tpsi.ogloszenia.web;

public class OfferFilter {
    private Integer manufacturerId;
    private Integer modelId;
    private Integer yearFrom;
    private Integer yearTo;
    private Integer fuelTypeId;

    /**
     * @return the manufacturerId
     */
    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public Integer getFuelTypeId() {
        return fuelTypeId;
    }

    public void setFuelTypeId(Integer fuelType) {
        this.fuelTypeId = fuelType;
    }

    public Integer getYearTo() {
        return yearTo;
    }

    public void setYearTo(Integer yearTo) {
        this.yearTo = yearTo;
    }

    public Integer getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(Integer yearFrom) {
        this.yearFrom = yearFrom;
    }

    /**
     * @return the modelId
     */
    public Integer getModelId() {
        return modelId;
    }

    /**
     * @param manufacturerId the manufacturerId to set
     */
    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    /**
     * @param modelId the modelId to set
     */
    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

}