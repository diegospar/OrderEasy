package com.example.ordereasy;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class RestaurantesInicio {

   //URLS slider
   public static final String url1 = "https://firebasestorage.googleapis.com/v0/b/ordereasy-" +
           "4510e.appspot.com/o/pexels-lorenzo-messina-6185937.jpg?alt=media&token=649464b6-c0c0-443a-" +
           "8a62-ea115e63227e";

    public static final String url2="https://firebasestorage.googleapis.com/v0/b/ordereasy-" +
            "4510e.appspot.com/o/pexels-waldemar-brandt-2290070.jpg?alt=media&token=fcbe1048-89a1-" +
            "47f8-b343-1d7dc58459e5";

    public static final String url3="https://firebasestorage.googleapis.com/v0/b/ordereasy" +
            "-4510e.appspot.com/o/pexels-lawrence-suzara-1581554.jpg?alt=media&token=ad28e668-4730" +
            "-4175-bccb-d2ab7818b30d";

    public static final String url4="https://firebasestorage.googleapis.com/v0/b/ordereasy" +
            "-4510e.appspot.com/o/pexels-one-shot-2788792.jpg?alt=media&token=4de401c9-0968-4723" +
            "-813c-d649666450a0";

    public static final String url5="https://firebasestorage.googleapis.com/v0/b/ordereasy" +
            "-4510e.appspot.com/o/pexels-anna-tis-6530100.jpg?alt=media&token=b05a64a5-ba1b-4154" +
            "-8d4a-fa706a37174f";


    public static final List<SlideModel> initR001(){
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/ordereasy-" +
                "4510e.appspot.com/o/Ensalada.jpg?alt=media&token=97222fad-2add-" +
                "4a82-b3a3-aee52f9f65e3","Ensalada de la casa", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/" +
                "ordereasy-4510e.appspot.com/o/cheescake.jpg?alt=media&token=10005e7b-aae8-467c-" +
                "bca4-92ada3814d3f","Cheescake de frutos rojos",ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/" +
                "ordereasy-4510e.appspot.com/o/Hamburguesa.jpg?alt=media&token=1f0e6e7f-0dfd-" +
                "434a-8fff-8d14b9695727","Hamburguesa de carne", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/" +
                "ordereasy-4510e.appspot.com/o/Crepa.jpg?alt=media&token=0f790f7c-8475-4356-" +
                "9dcc-80f9648da402","Crepas de nutela",ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/" +
                "ordereasy-4510e.appspot.com/o/cremaElote.jpg?alt=media&token=f7b5339d-7b73-" +
                "48ef-be57-1dfd6bb6db24","Crema de Elote", ScaleTypes.FIT));

        return slideModels;
    }

    public static final String nombreRestaurante(String idRestaurante){
        if ((idRestaurante.equals("R001"))){
            return "Hortenza";
        }else return "Restaurante";
    }

}
