/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Faiz
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Kasir {
    
    public static void tampilkanMenu(ArrayList kode, ArrayList menu) {
        System.out.println(String.format("%-10s %s", "Kode", "Menu"));
        for (int i = 0; i < kode.size(); i++) {
            System.out.println(String.format("%-10s %s", kode.get(i), menu.get(i)));
        }
    }
    
    public static void tambahMakanan(HashMap jumlahOrder, String Makanan, Scanner input) {   
        System.out.print("Jumlah Makanan: ");
        int jumlahMakanan = input.nextInt();
        jumlahOrder.put(Makanan, jumlahMakanan);
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> kode = new ArrayList<String>(Arrays.asList("n", "m", "b", "e", "a", "j", "s"));
        ArrayList<String> menu = new ArrayList<String>(Arrays.asList("Nasi Remes", "Mie Ayam", "Bakso", "Es Teh", "Air Cup", "Jus Buah", "selesai"));
        ArrayList<Integer> harga = new ArrayList<Integer>(Arrays.asList(10000, 8000, 8000, 4000, 2000, 6000));
        HashMap<String, Integer> jumlahOrder = new HashMap<String, Integer>();
        String pilihMenu;
        while (true) {
            Kasir.tampilkanMenu(kode, menu);
            System.out.print("Pilih Menu: ");
            pilihMenu = input.nextLine();
            if(pilihMenu.equals("s")) {
                break;
            } else if(pilihMenu.matches("[nmbeajas]")){
                int indexOf = kode.indexOf(pilihMenu);
                String makanan = menu.get(indexOf);
                Kasir.tambahMakanan(jumlahOrder, makanan, input);
            } else {
                System.out.println("Pilihan anda salah");
            }
            System.out.print("\n\n");
        }
        Kasir.hitungSubTotal(kode, menu, harga, jumlahOrder, input);
        /*do {            
            System.out.print("Pilih menu: ");
            pilihMenu = input.nextLine();
            if (pilihMenu.equals("s")) {
                break;
            }
            int indexOf = kode.indexOf(pilihMenu);
            System.out.print("Masukan jumlah: ");
            int jumlahBarang = input.nextInt();
            String makanan = menu.get(indexOf);
            jumlahOrder.put(makanan, jumlahBarang);
            //System.out.println(makanan + jumlahBarang + harga.get(indexOf));
        } while (pilihMenu != "s");
        */
        
        /*HashMap<String, Integer> hargaBarang = new HashMap<String, Integer>();
        hargaBarang.put("Nasi Remes", 10000);
        hargaBarang.put("Mie Ayam", 8000);
        hargaBarang.put("Bakso", 8000);
        hargaBarang.put("Es Teh Manis", 4000);
        hargaBarang.put("Air cup", 2000);
        hargaBarang.put("Jus Buah", 6000);*/
        
    }
    
    public static double hitungDiskon(int harga) {
        double hargaBaru = harga * 0.05;
        return hargaBaru;
    }
    
    public static void tampilGaris(int x) {
        for (int i = 0; i < x; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    public static void hitungSubTotal(ArrayList kode, ArrayList menu, ArrayList harga, HashMap jumlahOrder, Scanner input) {
        System.out.println(String.format("%-10s %-20s %s", "Kode", "Menu", "Jumlah"));
        int SubTotal = 0, Quantity = 0;
        for (Object object : jumlahOrder.keySet()) {
            int indexNomor = menu.indexOf(object);
            Object KodeM = kode.get(indexNomor);
            Object jumlahOrderMakanan = jumlahOrder.get(object);
            Object hargaMakanan = harga.get(indexNomor);
            int totalHarga = Integer.valueOf(jumlahOrderMakanan.toString()) * Integer.valueOf(hargaMakanan.toString());
            Quantity += Integer.valueOf(jumlahOrderMakanan.toString());
            SubTotal += totalHarga;
            System.out.println(String.format("%-10s %-20s", KodeM, object));
            System.out.println(String.format("%-10s %-20s %s", "", (jumlahOrderMakanan + " x " + object), hargaMakanan));
        }
        Kasir.tampilGaris(40);
        System.out.println(String.format("%-10s %-18s : %s","", "SubTotal", SubTotal));
        double diskon = Kasir.hitungDiskon(SubTotal);
        System.out.println(String.format("%-10s %-18s : -%s","", "Diskon 5%", diskon));
        Kasir.tampilGaris(40);
        System.out.println(String.format("%-10s %-18s : %s","", "Quantity", Quantity));
        double hargaDibayar = SubTotal - diskon;
        System.out.println(String.format("%-10s %-18s : %s","", "Total", hargaDibayar));
        Kasir.tampilGaris(40);
        boolean transaksi = true;
        do {            
            
            System.out.print("Jumlah Bayar: ");
            int jumlahBayar = input.nextInt();
            if (jumlahBayar == hargaDibayar) {
                transaksi = false;
            } else if(jumlahBayar > hargaDibayar) {
                System.out.println("Kembalian anda: "  + (jumlahBayar-hargaDibayar));
                transaksi = false;
            } else {
                System.out.println("Uang Anda Kurang: ");
            }
        } while (transaksi);
        System.out.println("Terimakasih \nSelamat Datang Kembali");
    }
}
