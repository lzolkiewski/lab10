package wizut.tpsi.ogloszenia.jpa;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Size(max = 255, min = 5) // max min długość
    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "year")
    @NotNull // nie null
    @Min(1900) // minimalna wartość
    private Integer year;

    @Column(name = "mileage")
    @NotNull
    @Min(0)
    private Integer mileage;

    @Column(name = "engine_size")
    @Min(0)
    private BigDecimal engineSize;

    @Column(name = "engine_power")
    @Min(0)
    private Integer enginePower;

    @Column(name = "doors")
    @NotNull
    @Min(1)
    @Max(5)
    private Integer doors;

    @Size(max = 30, min = 3)
    @Column(name = "colour")
    @NotNull
    private String colour;

    @Lob
    @Size(max = 65535, min = 5)
    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "price")
    @NotNull
    @Min(0)
    private Integer price;

    @Column(name = "add_date")
    private Date addDate;

    @JoinColumn(name = "model_id", referencedColumnName = "id")
    @ManyToOne
    @NotNull
    private CarModel model;

    @JoinColumn(name = "body_style_id", referencedColumnName = "id")
    @ManyToOne
    @NotNull
    private BodyStyle bodyStyle;

    @JoinColumn(name = "fuel_type_id", referencedColumnName = "id")
    @ManyToOne
    @NotNull
    private FuelType fuelType;

    public Offer() {

    }

    /**
     * @return the bodyStyle
     */
    public BodyStyle getBodyStyle() {
        return bodyStyle;
    }

    /**
     * @return the colour
     */
    public String getColour() {
        return colour;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the doors
     */
    public Integer getDoors() {
        return doors;
    }

    /**
     * @return the enginePower
     */
    public Integer getEnginePower() {
        return enginePower;
    }

    /**
     * @return the engineSize
     */
    public BigDecimal getEngineSize() {
        return engineSize;
    }

    /**
     * @return the fuelType
     */
    public FuelType getFuelType() {
        return fuelType;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the mileage
     */
    public Integer getMileage() {
        return mileage;
    }

    /**
     * @return the model
     */
    public CarModel getModel() {
        return model;
    }

    /**
     * @return the price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * @param bodyStyle the bodyStyle to set
     */
    public void setBodyStyle(BodyStyle bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    /**
     * @param colour the colour to set
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param doors the doors to set
     */
    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    /**
     * @param enginePower the enginePower to set
     */
    public void setEnginePower(Integer enginePower) {
        this.enginePower = enginePower;
    }

    /**
     * @param engineSize the engineSize to set
     */
    public void setEngineSize(BigDecimal engineSize) {
        this.engineSize = engineSize;
    }

    /**
     * @param fuelType the fuelType to set
     */
    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param mileage the mileage to set
     */
    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    /**
     * @param model the model to set
     */
    public void setModel(CarModel model) {
        this.model = model;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param year the year to set
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
}