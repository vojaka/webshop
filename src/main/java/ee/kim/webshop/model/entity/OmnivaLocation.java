package ee.kim.webshop.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OmnivaLocation{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        public String zIP;
        public String nAME;
        public String tYPE;
        public String a0_NAME;
        public String a1_NAME;
        public String a2_NAME;
        public String a3_NAME;
        public String a4_NAME;
        public String a5_NAME;
        public String a6_NAME;
        public String a7_NAME;
        public String a8_NAME;
        public String x_COORDINATE;
        public String y_COORDINATE;
        public String sERVICE_HOURS;
        public String tEMP_SERVICE_HOURS;
        public String tEMP_SERVICE_HOURS_UNTIL;
        public String tEMP_SERVICE_HOURS_2;
        public String tEMP_SERVICE_HOURS_2_UNTIL;
        public String comment_est;
        public String comment_eng;
        public String comment_rus;
        public String comment_lav;
        public String comment_lit;
        public Date mODIFIED;
    }
