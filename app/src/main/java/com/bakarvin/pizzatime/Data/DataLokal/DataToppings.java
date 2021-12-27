package com.bakarvin.pizzatime.Data.DataLokal;

import com.bakarvin.pizzatime.Model.ModelTopping;
import com.bakarvin.pizzatime.R;

import java.util.ArrayList;

public class DataToppings {
    private static int[] imgTopping = {
            R.raw.mushroom,
            R.raw.pepperoni,
            R.raw.cheese,
            R.raw.sausage,
            R.raw.bolives,
            R.raw.onion,
            R.raw.gpepper,
            R.raw.basil,
            R.raw.garlic,
    };
    private static Boolean[] clickedTopping = {
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false
    };
    private static String[] namaTopping = {
            "Mushroom",
            "Pepperoni",
            "Extra Cheese",
            "Sausage",
            "Black Olives",
            "Onion",
            "Green Pepper",
            "Fresh Basil",
            "Fresh Garlic"
    };
    private static int[] hargaTopping = {
//            "3",
//            "5",
//            "4",
//            "5",
//            "4",
//            "3",
//            "4",
//            "3",
//            "3"
            R.string.ex3,
            R.string.ex5,
            R.string.ex4,
            R.string.ex5,
            R.string.ex4,
            R.string.ex3,
            R.string.ex4,
            R.string.ex3,
            R.string.ex3



    };
    public static ArrayList<ModelTopping> getListData() {
        ArrayList<ModelTopping> list = new ArrayList<>();
        for (int position = 0; position < namaTopping.length; position++) {
            ModelTopping topping = new ModelTopping();
            topping.setNamaTopping(namaTopping[position]);
            topping.setHargaTopping(hargaTopping[position]);
            topping.setImgTopping(imgTopping[position]);
            topping.setClickedTopping(clickedTopping[position]);
            list.add(topping);
        }
        return list;
    }
}
