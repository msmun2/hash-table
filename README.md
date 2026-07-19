Implementasi Hash Table dengan Separate Chaining Menggunakan Java
Deskripsi Program

Program ini merupakan implementasi sederhana struktur data Hash Table menggunakan bahasa pemrograman Java.

Hash Table digunakan untuk menyimpan data dalam bentuk pasangan:

key dan value

Contoh:

1, Apel
11, Jeruk
21, Mangga

Program ini tidak menggunakan struktur data bawaan Java seperti:

HashMap
LinkedList

Linked List dibuat secara manual menggunakan kelas Node.

Metode yang digunakan untuk mengatasi tabrakan adalah Separate Chaining.

Apa Itu Hash Table?

Hash Table adalah struktur data yang digunakan untuk menyimpan dan mencari data dengan cepat berdasarkan sebuah kunci atau key.

Setiap key akan dihitung menggunakan fungsi hash untuk menentukan indeks tempat data disimpan.

Pada program ini, ukuran Hash Table adalah:

10

Indeks yang tersedia adalah:

0 sampai 9
Fungsi Hash

Fungsi hash yang digunakan adalah metode modulo.

int hash(int key) {
    return key % 10;
}

Rumusnya adalah:

index = key mod 10

Contoh:

1 mod 10  = 1
11 mod 10 = 1
21 mod 10 = 1

Key 1, 11, dan 21 menghasilkan indeks yang sama, yaitu indeks 1.

Kondisi tersebut disebut sebagai collision atau tabrakan.

Separate Chaining

Separate Chaining adalah metode untuk menangani tabrakan pada Hash Table dengan menggunakan Linked List.

Jika beberapa key menghasilkan indeks yang sama, data akan disimpan secara berurutan pada Linked List di indeks tersebut.

Contoh:

Indeks 1 : [1, Apel] -> [11, Jeruk] -> [21, Mangga] -> null

Semua data tersebut berada pada indeks 1, tetapi disimpan dalam Node yang berbeda.

Penjelasan Kode
1. Class Main
public class Main {

Class Main merupakan class utama dari program.

Nama file Java harus sama dengan nama class utama, sehingga kode program disimpan dengan nama:

Main.java
2. Class Node
static class Node {
    int key;
    String value;
    Node next;
}

Class Node digunakan untuk membuat Linked List secara manual.

Setiap Node memiliki tiga variabel.

Key
int key;

Variabel key digunakan sebagai kunci data.

Contoh:

1
11
21
Value
String value;

Variabel value digunakan untuk menyimpan nilai dari key.

Contoh:

Apel
Jeruk
Mangga
Next
Node next;

Variabel next digunakan untuk menunjuk ke Node berikutnya.

Jika tidak ada Node berikutnya, nilai next adalah:

null
3. Constructor Node
Node(int key, String value) {
    this.key = key;
    this.value = value;
    this.next = null;
}

Constructor digunakan untuk memberikan nilai awal ketika sebuah Node dibuat.

Contoh:

Node baru = new Node(1, "Apel");

Node tersebut memiliki isi:

key   = 1
value = Apel
next  = null
4. Class HashTable
static class HashTable {

Class HashTable digunakan untuk mengatur seluruh proses penyimpanan data.

Class ini memiliki beberapa operasi, yaitu:

insert()
search()
remove()
display()
5. Array Hash Table
Node[] table = new Node[10];

Kode tersebut membuat sebuah array bernama table dengan ukuran 10.

Setiap elemen array memiliki tipe data Node.

Indeks array dimulai dari:

0 sampai 9

Pada awal program, seluruh indeks masih bernilai null.

Indeks 0 : null
Indeks 1 : null
Indeks 2 : null
Indeks 3 : null
Indeks 4 : null
Indeks 5 : null
Indeks 6 : null
Indeks 7 : null
Indeks 8 : null
Indeks 9 : null
6. Method Hash
int hash(int key) {
    return key % 10;
}

Method hash() digunakan untuk menentukan indeks berdasarkan key.

Contoh:

hash(11);

Perhitungannya adalah:

11 mod 10 = 1

Artinya, key 11 akan disimpan pada indeks 1.

7. Method Insert
void insert(int key, String value) {

Method insert() digunakan untuk memasukkan key dan value ke dalam Hash Table.

Contoh:

data.insert(1, "Apel");
Menentukan Indeks
int index = hash(key);

Key dihitung menggunakan fungsi hash.

Untuk key 1:

1 mod 10 = 1

Data akan dimasukkan ke indeks 1.

Membuat Node Baru
Node baru = new Node(key, value);

Kode tersebut membuat Node baru berdasarkan key dan value yang diberikan.

Memeriksa Indeks
if (table[index] == null) {
    table[index] = baru;
}

Jika indeks masih kosong, Node baru langsung dimasukkan ke indeks tersebut.

Contoh:

Indeks 1 : [1, Apel] -> null
Jika Terjadi Tabrakan
else {
    Node bantu = table[index];

Jika indeks sudah memiliki data, variabel bantu digunakan untuk menelusuri Linked List.

while (bantu.next != null) {
    bantu = bantu.next;
}

Perulangan berjalan sampai menemukan Node terakhir.

bantu.next = baru;

Node baru kemudian ditambahkan setelah Node terakhir.

Contoh setelah memasukkan key 1, 11, dan 21:

Indeks 1 : [1, Apel] -> [11, Jeruk] -> [21, Mangga] -> null
8. Method Search
String search(int key) {

Method search() digunakan untuk mencari value berdasarkan key.

Contoh:

data.search(11);
Menentukan Indeks
int index = hash(key);

Untuk key 11:

11 mod 10 = 1

Pencarian dilakukan pada Linked List di indeks 1.

Menelusuri Linked List
Node bantu = table[index];

while (bantu != null) {

Variabel bantu digunakan untuk memeriksa setiap Node.

Memeriksa Key
if (bantu.key == key) {
    return bantu.value;
}

Jika key ditemukan, program mengembalikan value dari key tersebut.

Contoh:

Key 11 ditemukan
Value = Jeruk
Berpindah ke Node Berikutnya
bantu = bantu.next;

Jika key belum ditemukan, pencarian dilanjutkan ke Node berikutnya.

Data Tidak Ditemukan
return "Tidak ditemukan";

Jika seluruh Node telah diperiksa dan key tidak ditemukan, program mengembalikan pesan:

Tidak ditemukan
9. Method Remove
void remove(int key) {

Method remove() digunakan untuk menghapus data berdasarkan key.

Menentukan Indeks
int index = hash(key);

Program menghitung indeks dari key yang akan dihapus.

Variabel Bantu
Node bantu = table[index];
Node sebelumnya = null;

Variabel bantu digunakan untuk memeriksa Node saat ini.

Variabel sebelumnya digunakan untuk menyimpan Node sebelum Node saat ini.

Mencari Key
while (bantu != null) {

Program menelusuri Linked List sampai key ditemukan atau sampai akhir Linked List.

Menghapus Node Pertama
if (sebelumnya == null) {
    table[index] = bantu.next;
}

Jika sebelumnya masih null, berarti data yang dihapus berada pada Node pertama.

Contoh sebelum dihapus:

[1, Apel] -> [11, Jeruk] -> null

Setelah key 1 dihapus:

[11, Jeruk] -> null
Menghapus Node Tengah atau Terakhir
else {
    sebelumnya.next = bantu.next;
}

Jika data berada di tengah atau akhir Linked List, Node sebelumnya akan langsung dihubungkan dengan Node setelah data yang dihapus.

Contoh sebelum key 11 dihapus:

[1, Apel] -> [11, Jeruk] -> [21, Mangga] -> null

Setelah key 11 dihapus:

[1, Apel] -> [21, Mangga] -> null
Pesan Berhasil
System.out.println("Data berhasil dihapus");
return;

Jika data berhasil dihapus, program menampilkan pesan dan menghentikan method.

Data Tidak Ditemukan
System.out.println("Data tidak ditemukan");

Pesan tersebut ditampilkan apabila key yang akan dihapus tidak ada di dalam Hash Table.

10. Method Display
void display() {

Method display() digunakan untuk menampilkan seluruh isi Hash Table.

Perulangan Setiap Indeks
for (int i = 0; i < 10; i++) {

Perulangan dilakukan dari indeks 0 sampai 9.

Menampilkan Nomor Indeks
System.out.print("Indeks " + i + " : ");

Kode tersebut menampilkan nomor indeks.

Mengambil Node Pertama
Node bantu = table[i];

Variabel bantu menunjuk ke Node pertama pada setiap indeks.

Menampilkan Isi Linked List
while (bantu != null) {
    System.out.print(
        "[" + bantu.key + ", " + bantu.value + "] -> "
    );

    bantu = bantu.next;
}

Program menampilkan key dan value dari setiap Node, kemudian berpindah ke Node berikutnya.

Menampilkan Null
System.out.println("null");

null menunjukkan bahwa Linked List sudah berakhir atau indeks tidak memiliki data.

Penjelasan Method Main
Membuat Objek Hash Table
HashTable data = new HashTable();

Kode tersebut membuat objek Hash Table bernama data.

Memasukkan Data
data.insert(1, "Apel");
data.insert(11, "Jeruk");
data.insert(21, "Mangga");
data.insert(2, "Pisang");
data.insert(12, "Anggur");

Hasil perhitungan fungsi hash:

Key	Perhitungan	Indeks
1	1 mod 10	1
11	11 mod 10	1
21	21 mod 10	1
2	2 mod 10	2
12	12 mod 10	2

Data pada indeks 1:

[1, Apel] -> [11, Jeruk] -> [21, Mangga] -> null

Data pada indeks 2:

[2, Pisang] -> [12, Anggur] -> null
Menampilkan Hash Table
data.display();

Kode tersebut memanggil method display() untuk menampilkan seluruh data.

Mencari Data
System.out.println(data.search(11));

Program mencari key 11.

Key 11 berada pada indeks:

11 mod 10 = 1

Value yang ditemukan adalah:

Jeruk
Menghapus Data
data.remove(21);

Program menghapus data dengan key 21.

Sebelum dihapus:

[1, Apel] -> [11, Jeruk] -> [21, Mangga] -> null

Setelah dihapus:

[1, Apel] -> [11, Jeruk] -> null
Contoh Output Program
Isi Hash Table:
Indeks 0 : null
Indeks 1 : [1, Apel] -> [11, Jeruk] -> [21, Mangga] -> null
Indeks 2 : [2, Pisang] -> [12, Anggur] -> null
Indeks 3 : null
Indeks 4 : null
Indeks 5 : null
Indeks 6 : null
Indeks 7 : null
Indeks 8 : null
Indeks 9 : null

Pencarian key 11:
Jeruk

Menghapus key 21:
Data berhasil dihapus

Isi setelah dihapus:
Indeks 0 : null
Indeks 1 : [1, Apel] -> [11, Jeruk] -> null
Indeks 2 : [2, Pisang] -> [12, Anggur] -> null
Indeks 3 : null
Indeks 4 : null
Indeks 5 : null
Indeks 6 : null
Indeks 7 : null
Indeks 8 : null
Indeks 9 : null
Cara Menjalankan Program
1. Simpan File

Simpan kode program dengan nama:

Main.java
2. Buka Terminal

Masuk ke folder tempat file Main.java disimpan.

3. Compile Program

Jalankan perintah:

javac Main.java

Jika tidak terdapat error, Java akan membuat file:

Main.class
4. Jalankan Program

Gunakan perintah:

java Main

Output Hash Table akan ditampilkan pada terminal.

Operasi yang Tersedia
Method	Fungsi
insert(key, value)	Memasukkan key dan value
search(key)	Mencari value berdasarkan key
remove(key)	Menghapus data berdasarkan key
display()	Menampilkan seluruh isi Hash Table
