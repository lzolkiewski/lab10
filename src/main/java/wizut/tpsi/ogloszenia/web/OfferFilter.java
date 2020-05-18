package wizut.tpsi.ogloszenia.web;

public class OfferFilter {
    private Integer manufacturerId;
    private Integer modelId;

    /**
     * @return the manufacturerId
     */
    public Integer getManufacturerId() {
        return manufacturerId;
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