
import java.util.*;
import java.util.function.Predicate;

class Orang {
    String nama;
    String ktp;
    String email;

    Orang(String nama, String ktp, String email) {
        this.nama = nama;
        this.ktp = ktp;
        this.email = email;
    }

    public String toString() {
        return nama + " | " + ktp + " | " + email;
    }
}

public class MainAnonymous {
    public static void main(String[] args) {

        List<Orang> daftar = new ArrayList<>();

        // Data awal
        daftar.add(new Orang("Daniel", "12345", "daniel@gmail.com"));
        daftar.add(new Orang("Jason", "23456", "jason@gmail.com"));
        daftar.add(new Orang("Andi", "34567", "andi@yahoo.com"));
        daftar.add(new Orang("Budi", "45678", "budi@gmail.com"));
        daftar.add(new Orang("Cahya", "56789", "cahya@yahoo.com"));

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Search");
            System.out.println("2. Sort");
            System.out.println("3. Tampilkan Semua");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");

            int pilihan = input.nextInt();
            input.nextLine();

            if (pilihan == 0) {
                break;
            }

            if (pilihan == 1) {
                System.out.print("Masukkan keyword: ");
                String keyword = input.nextLine().toLowerCase();

                List<Orang> hasil = daftar.stream()
                    .filter(new Predicate<Orang>() {
                        public boolean test(Orang o) {
                            return o.nama.toLowerCase().contains(keyword) ||
                                   o.ktp.contains(keyword) ||
                                   o.email.toLowerCase().contains(keyword);
                        }
                    })
                    .collect(java.util.stream.Collectors.toList());

                hasil.forEach(System.out::println);
            }

            else if (pilihan == 2) {
                System.out.print("Sort berdasarkan (nama/ktp/email): ");
                String field = input.nextLine().toLowerCase();

                System.out.print("Urutan (asc/desc): ");
                String urutan = input.nextLine().toLowerCase();

                daftar.sort(new Comparator<Orang>() {
                    public int compare(Orang a, Orang b) {
                        int hasil = 0;

                        if (field.equals("nama"))
                            hasil = a.nama.compareToIgnoreCase(b.nama);
                        else if (field.equals("ktp"))
                            hasil = a.ktp.compareTo(b.ktp);
                        else if (field.equals("email"))
                            hasil = a.email.compareToIgnoreCase(b.email);

                        return urutan.equals("desc") ? -hasil : hasil;
                    }
                });

                System.out.println("Data berhasil diurutkan!");
                daftar.forEach(System.out::println);
            }

            else if (pilihan == 3) {
                daftar.forEach(System.out::println);
            }
        }

        input.close();
    }
}
