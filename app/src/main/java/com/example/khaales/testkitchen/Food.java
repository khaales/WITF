package com.example.khaales.testkitchen;

/**
 * Created by khaales on 1/26/18.
 */

public class Food {

        public String Date;
        public String Quantity;

        public Food(){}
        public Food(String Name, String Date) {
            this.Date = Date;
            this.Quantity = Quantity;
        }

        @Override
        public String toString() {
            return this.Name + ": " + this.Date;
        }

}
