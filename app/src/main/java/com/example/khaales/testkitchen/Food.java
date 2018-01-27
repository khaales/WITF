package com.example.khaales.testkitchen;

/**
 * Created by khaales on 1/26/18.
 */

public class Food {

        public String Date;
        public String Quantity;
        public String Present;

        public Food(){}
        public Food(String Name, String Date, String Present) {
            this.Date = Date;
            this.Quantity = Quantity;
            this.Present = Present;
        }

        @Override
        public String toString() {
            return this.Date + ": " + this.Quantity + ": " + this.Present;
        }

}
