package com.luilli.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity  {

    ProgressBar pb;
    public ArrayList<Bien> A = new ArrayList();
    public  int k;
    public  int numerodecombinaciones=0;
    public static List<List<Bien>> combinaciones = new ArrayList();
    public long nCombTotales= 0;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb=findViewById(R.id.progressBar);

        Bien b1 = new Bien("revolta",1);
        Bien b2 = new Bien("bouzas",2);
        Bien b3 = new Bien("nazo",3);
        Bien b4 = new Bien("gradin",4);
        Bien b5 = new Bien("faro",5);
        Bien b6 = new Bien("niño de agre",6);
        Bien b7 = new Bien("gradin",7);
        Bien b8 = new Bien("faro",8);
        Bien b9 = new Bien("niño de agre",9);
        Bien b10 = new Bien("su outeiro",10);
        A.add(b1);A.add(b2);A.add(b3);A.add(b4);A.add(b5);
        //A.add(b6);//A.add(b7);A.add(b8);A.add(b9);A.add(b10);
        k =A.size();

        for(int l=1; l<=k;l++)
        {
            nCombTotales+=nCombinaciones(A.size(),l);
        }

        pb.setMax((int)nCombTotales);

        for(int i=1;i<=k;i++)
        {
            findCombinations(A,i);
        }

        for(List<Bien> b:combinaciones)
        {
            for(Bien bien:b)
            {
                Log.i("hola",String.valueOf(bien.getNombre()));
            }
        }
    }

    public static long factorial(long numero)
    {
        long factorial=1;
        while ( numero!=0) {
            factorial=factorial*numero; numero--;
        }
        return factorial;
    }

    public static long nCombinaciones(long numero,long orden)
    {
        long nCombinaciones=factorial(numero)/(factorial(orden)*(factorial(numero-orden)));
        return nCombinaciones;
    }

    public void findCombinations(ArrayList<Bien> A, int i, int k,
                                 Set<List<Bien>> subarrays,
                                 List<Bien> out)


    {
        // entrada inválida
        if (A.size() == 0 || k > A.size()) {
            return;
        }

        // caso base: el tamaño de la combinación es `k`
        if (k == 0) {
            subarrays.add(new ArrayList<>(out));
            numerodecombinaciones++;
            pb.setProgress(numerodecombinaciones);
            return;
        }

        // comienza desde el siguiente índice hasta el último índice
        for (int j = i; j < A.size(); j++)
        {
            // agrega el elemento actual `A[j]` a la solución y repite para el siguiente índice
            // `j+1` con un elemento menos `k-1`
            out.add(A.get(j));
            findCombinations(A, j + 1, k - 1, subarrays, out);
            out.remove(out.size() - 1);        // retractarse

        }
    }

    public Set<List<Bien>> findCombinations(ArrayList<Bien> A, int k)
    {
        Set<List<Bien>> subarrays = new HashSet<>();
        findCombinations(A, 0, k, subarrays, new ArrayList<>());
        combinaciones.addAll(subarrays);
        return subarrays;
    }
}