TUGAS PROYEK PRAKTIK PEMROGRAMAN 1
Nama   = Shakti Dewangga Chandrabayu
NIM    = 21537144022
Kelas  = J2
Prodi  = S1 Teknologi Informasi
Bahasa = Java
*/


import java.util.Scanner;

    public class Siakad {
        Mahasiswa[] mahasiswa = new Mahasiswa[1000];
        private int jumlahData = 0;

        public static void main(String[] args) {

            System.out.println();
            Siakad siakad = new Siakad();
            int menu = 0; // deklarasi menu
            while (menu!=5) { //nampilin menu
                menu = displayMenu(); 
                if (menu == 1) { // lihat data
                    siakad.lihatData();
                } else if (menu == 2) {
                    siakad.tambahData();
                } else if (menu == 3 ) {
                    siakad.ratarataIpk();
                } else if (menu == 4) {
                    siakad.cariDataViaNama();
                } else if (menu == 5) {
                    siakad.cariDataViaNIM();
                } else if (menu == 6) {
                    siakad.editNama();
                } else if (menu == 7) {
                    siakad.editNIM();
                } else if (menu == 8) {
                    siakad.hapusData();
                } 
            }
        }

        private static int displayMenu() {
            System.out.println();
            System.out.println("Selamat datang di Sistem Informasi Akademik Universitas Negeri Yogyakarta");
            System.out.println("Menu : ");
            System.out.println("1. Lihat Data");
            System.out.println("2. Tambah Data");
            System.out.println("3. Rata-rata IPK");
            System.out.println("4. Cari Nama");
            System.out.println("5. Cari NIM");
            System.out.println("6. Edit Nama");
            System.out.println("7. Edit NIM");
            System.out.println("8. Hapus Data NIM");
            System.out.println("9. Keluar");
            System.out.print("Silahkan pilih menu = ");
            Scanner scanMenu = new Scanner (System.in);
            int menu = scanMenu.nextInt();
            return menu;
        }

        private void lihatData() {
            if (jumlahData == 0) {
                System.out.println();
                System.out.println("Data belum ter-input");
            } else {
                System.out.println();
                System.out.println("Data mahasiswa : ");
                System.out.printf("%10s %20s %5s %5s %5s", "NIM", "NAMA LENGKAP", "SEMESTER", "IPK", "NAMA AYAH ATAU WALI");
                System.out.println();
                System.out.println("--------------------------------------------------------------------------------------------------");
                for (int i = 0; i < jumlahData; i++) {
                   mahasiswa[i].getDetail();
                }
                System.out.println();
                ratarataIpk();
            }
        }

        private void tambahData() {
            Scanner scan = new Scanner(System.in);
            Scanner scanTwo = new Scanner (System.in);

            System.out.println();

            System.out.print("Masukkan 11 digit NIM                  : ");
            String studID = scan.nextLine();
    
            System.out.print("Masukkan nama lengkap Mahasiswa        : ");
            String fullName = scan.nextLine();

            System.out.print("Masukkan semester Mahasiswa            : ");
            int studSems = scan.nextInt();

            double gpa = 0 ;
            try {
            System.out.print("Masukkan IPK Mahasiswa                 : ");
            gpa = scan.nextDouble();
            } catch (Exception e) {
                System.out.println("Anda harus memasukan IPK dalam bentuk bilangan desimal.");
            }
            
            System.out.print("Masukkan nama Ayah atau Wali Mahasiswa : ");
            String fathersName = scanTwo.nextLine();

            Mahasiswa inputMahasiswa = new Mahasiswa (studID, fullName, studSems, gpa, gpa); // set
            inputMahasiswa.setfathersName(fathersName); // set
            mahasiswa[jumlahData] = inputMahasiswa; 
            jumlahData++; // ngasih tau jumlah data naik terus
            lihatData();
        }

        // kalau mau cari, edit, atau hapus, harus buat get indexnya dulu.

        public void cariDataViaNama() { // buat index dlu
            Scanner scan = new Scanner(System.in);
            System.out.print("Masukkan nama Mahasiswa yang ingin dicari :");
            String fullName = scan.nextLine();
            int index = getIndexbyNama(fullName);

            if (index == -1) {
                System.out.println("Nama yang anda cari belum tercantum di data.");
            } else {
                System.out.printf("%10s %20s %5s %5s %5s", "NIM", "NAMA LENGKAP", "SEMESTER", "IPK", "NAMA AYAH ATAU WALI");
                System.out.println();
                System.out.println("--------------------------------------------------------------------------------------------------");
                mahasiswa[index].getDetail();
            }
        }

        public int getIndexbyNama(String fullName) {  // harus ada kalo mau nyari
            for (int i = 0; i < jumlahData; i++) {
                if (mahasiswa[i].getfullName().equals(fullName)) {
                    return i;
                }
            }
            return -1;
        }

        public void cariDataViaNIM() { // buat baru
            Scanner scan = new Scanner(System.in);
            System.out.print("Masukkan NIM yang ingin dicari :");
            String studID = scan.nextLine();
            int index = getIndexByNIM(studID);

            if (index == -1) {
                System.out.println("NIM yang anda cari belum tercantum di data.");
            } else {
                System.out.printf("%10s %20s %5s %5s %5s", "NIM", "NAMA LENGKAP", "SEMESTER", "IPK", "NAMA AYAH ATAU WALI");
                System.out.println();
                System.out.println("--------------------------------------------------------------------------------------------------");
                mahasiswa[index].getDetail();
            }
        }

        public int getIndexByNIM (String studID) { // buat int baru buat cek apakah sama yang dicari apa tidak
            for (int i = 0; i < jumlahData; i++) {
                if (mahasiswa[i].getstudID().equals(studID)) {
                    return i;
                }
            }
        return -1;
        }

        public int getIndexByGPA (double gpa) { // buat int baru buat cek apakah sama yang dicari apa tidak
            for (int i = 0; i < jumlahData; i++) {
                if (mahasiswa[i].getgpa()==(gpa)) {
                    return i;
                }
            }
        return -1;
        }

        public void editNama() {
            Scanner scan = new Scanner (System.in);
            System.out.print("Masukkan nama Mahasiswa yang ingin diedit : ");
            String fullName = scan.nextLine();
            int index = getIndexbyNama(fullName);

            if (index == -1) {
                System.out.println("Nama yang Anda masukkan tidak tersedia.");
            } else {
                System.out.print("Nama baru Mahasiswa : ");
                String nama = scan.nextLine();
                mahasiswa[index].setfullName(nama);
                System.out.printf("%10s %20s %5s %5s %5s", "NIM", "NAMA LENGKAP", "SEMESTER", "IPK", "NAMA AYAH ATAU WALI");
                System.out.println();
                System.out.println("--------------------------------------------------------------------------------------------------");
                mahasiswa[index].getDetail();
            }
        }

        public void editNIM() { // buat index dulu ya
            Scanner scan = new Scanner (System.in);
            System.out.print("Masukkan NIM yang ingin diedit : ");
            String studID = scan.nextLine();
            int index = getIndexByNIM(studID);

            if (index == -1) {
                System.out.println("NIM yang Anda masukkan tidak tersedia.");
            } else {
                System.out.print("NIM baru : ");
                String nim = scan.nextLine();
                mahasiswa[index].setstudID(nim);
                System.out.printf("%10s %20s %5s %5s %5s", "NIM", "NAMA LENGKAP", "SEMESTER", "IPK", "NAMA AYAH ATAU WALI");
                System.out.println();
                System.out.println("--------------------------------------------------------------------------------------------------");
                mahasiswa[index].getDetail();
            }
        }

        public void hapusData() { //buat index jugak
            Scanner scan = new Scanner (System.in);
            System.out.print("Masukkan NIM yang ingin dihapus : ");
            String studID = scan.nextLine();
            int index = getIndexByNIM(studID);

            if (index == -1) {
                System.out.println("Data yang ingin anda hapus tidak tersedia.");
            } else {
                for (int i = 0; i < jumlahData; i++) {
                    mahasiswa[i] = mahasiswa[i+1];
                }
            jumlahData--;
            lihatData();
            }
        }
        
        public void ratarataIpk() {
            double totalIpk = 0.0;
            for (int i = 0; i < jumlahData; i++) {
                totalIpk += mahasiswa[i].getgpa();
            }
            double ratarata = totalIpk / jumlahData;

            System.out.println("Rata-rata IPK dari Mahasiswa tersebut adalah : " + ratarata);
        }
    }
