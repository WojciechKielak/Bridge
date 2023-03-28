package com.example.demo2;

import java.util.Random;

public class Losowanie extends Thread {
    Random random = new Random();
   private static boolean stanSwiadomosci = true;

    public static void setofflosowanie(){
        stanSwiadomosci = false;
    }

    public void run() {
        int iloscPrawer=0,iloscLewej=0;
        while (stanSwiadomosci) {


            try {
                HelloApplication.semafor.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                HelloApplication.semM.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if( (iloscPrawer>=10 || iloscLewej>=10) && (HelloApplication.ileL > 0 && HelloApplication.ileP > 0) ){
                if(iloscLewej>=110){
                    iloscPrawer=0;
                    iloscLewej=0;
                    HelloApplication.ileP--;
                    Pojawaienie.textNP.setText(HelloApplication.ileP+ "");
                    HelloApplication.semP.release();
                }else{
                    iloscPrawer=0;
                    iloscLewej=0;
                    HelloApplication.ileL--;
                    Pojawaienie.textNL.setText(HelloApplication.ileL+ "");
                    HelloApplication.semL.release();
                }
            }
            else{
                if (HelloApplication.ileL > 0 && HelloApplication.ileP > 0 ) {
                    int a = random.nextInt(2);

                    if (a == 0) {
                        iloscLewej++;
                        HelloApplication.ileL--;
                        HelloApplication.semL.release();

                    } else {
                        iloscPrawer++;
                        HelloApplication.ileP--;
                        HelloApplication.semP.release();

                    }
                } else if (HelloApplication.ileL > 0) {
                    iloscLewej++;
                    HelloApplication.ileL--;
                    Pojawaienie.textNL.setText(HelloApplication.ileL+ "");
                    HelloApplication.semL.release();

                } else {
                    iloscPrawer++;
                    HelloApplication.ileP--;
                    Pojawaienie.textNP.setText(HelloApplication.ileP+ "");
                    HelloApplication.semP.release();

                }
            }

        }
    }
}
