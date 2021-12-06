package myExercises;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class Bookingdates {

    /*
    POJO'da olmasi gerekenler :
    https://www.jsonschema2pojo.org
1Jsonda key olanlar icin variable oluşturun ve variableların acces modifierlarını private yapin
2) Her variable icin getter ve setter methodlarini oluşturun
3)Parametresiz constructor oluşturun (icinde super olmasın)
4)oluştuduğumuz variabları parametre kabul eden parametreli constructor oluşturun (icinde super olmasın)
5)toString() methodu oluşturun
     */
        @JsonProperty("checkin")
        private String checkin;
        @JsonProperty("checkout")
        private String checkout;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("checkin")
        public String getCheckin() {
            return checkin;
        }

        @JsonProperty("checkin")
        public void setCheckin(String checkin) {
            this.checkin = checkin;
        }

        @JsonProperty("checkout")
        public String getCheckout() {
            return checkout;
        }

        @JsonProperty("checkout")
        public void setCheckout(String checkout) {
            this.checkout = checkout;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    public Bookingdates() {
    }

    public Bookingdates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "Bookingdates{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
