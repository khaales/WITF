package com.example.khaales.testkitchen;

/**
 * Created by khaales on 1/26/18.
 */

public class Food {

        public String Name;
        public String Date;

        public Food(){}
        public Food(String Name, String Date) {
            this.Name = Name;
            this.Date = Date;
        }

        @Override
        public String toString() {
            return this.Name + ": " + this.Date;
        }

}
