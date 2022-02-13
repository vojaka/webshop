package ee.kim.webshop.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OmnivaLocation {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String zip;
        private String name;
        private String type;
        private String a0Name;
        private String a1Name;
        private String a2Name;
        private String a3Name;
        private String a4Name;
        private String a5Name;
        private String a6Name;
        private String a7Name;
        private String a8Name;
        private String xCoordinate;
        private String yCoordinate;
        private String serviceHours;
        private String tempServiceHours;
        private String tempServiceHoursUntil;
        private String tempServiceHours2;
        private String tempServiceHours2_Until;
        private String commentEst;
        private String commentEng;
        private String commentRus;
        private String commentLav;
        private String commentLit;
        private String modified;
    }
