package wizut.tpsi.ogloszenia.web;

public class OfferSorter {
    private Integer price;
    private Integer year;
    private Integer addDate;

    public Integer getPrice() {
        return price;
    }

    public Integer getAddDate() {
        return addDate;
    }

    public void setAddDate(Integer addDate) {
        this.addDate = addDate;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}