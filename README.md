# Implementasi Hash Table dengan Separate Chaining

Program ini merupakan implementasi sederhana struktur data **Hash Table** menggunakan bahasa pemrograman Java.

Hash Table dibuat menggunakan array berukuran `10` dan menggunakan metode **Separate Chaining** untuk menangani tabrakan atau *collision*.

Program tidak menggunakan struktur data bawaan Java seperti:

* `HashMap`
* `Hashtable`
* `LinkedList`

Linked List dibuat secara manual menggunakan class `Node`.

---

## Struktur Program

Program terdiri dari tiga bagian utama:

```text
Main
├── Node
├── HashTable
└── main()
```

Keterangan:

| Bagian      | Fungsi                                               |
| ----------- | ---------------------------------------------------- |
| `Node`      | Menyimpan `key`, `value`, dan alamat Node berikutnya |
| `HashTable` | Menjalankan operasi Hash Table                       |
| `main()`    | Menjalankan pengujian program                        |

---

# Penjelasan Kode Program

## 1. Class `Node`

```java
static class Node {
    int key;
    String value;
    Node next;

    Node(int key, String value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}
```

Class `Node` digunakan untuk membuat Linked List secara manual.

Setiap Node memiliki tiga variabel:

| Variabel | Tipe Data | Fungsi                      |
| -------- | --------- | --------------------------- |
| `key`    | `int`     | Menyimpan kunci data        |
| `value`  | `String`  | Menyimpan nilai dari key    |
| `next`   | `Node`    | Menunjuk ke Node berikutnya |

Contoh sebuah Node:

```text
key   = 1
value = Apel
next  = null
```

Variabel `next` bernilai `null` apabila tidak terdapat Node berikutnya.

---

## 2. Constructor `Node`

```java
Node(int key, String value) {
    this.key = key;
    this.value = value;
    this.next = null;
}
```

Constructor digunakan untuk memberikan nilai awal ketika sebuah Node dibuat.

Contoh:

```java
Node baru = new Node(1, "Apel");
```

Node tersebut akan menyimpan:

```text
Key   : 1
Value : Apel
Next  : null
```

---

## 3. Array Hash Table

```java
Node[] table = new Node[10];
```

Kode tersebut membuat array Hash Table dengan ukuran `10`.

Indeks Hash Table dimulai dari:

```text
0 sampai 9
```

Setiap indeks menyimpan Node pertama dari sebuah Linked List.

Kondisi awal Hash Table:

```text
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
```

---

## 4. Method `hash()`

```java
int hash(int key) {
    return key % 10;
}
```

Method `hash()` digunakan untuk menentukan indeks berdasarkan key.

Fungsi hash menggunakan operasi modulo:

```text
index = key mod 10
```

Contoh:

| Key | Perhitungan | Indeks |
| --: | ----------- | -----: |
|   1 | `1 mod 10`  |      1 |
|  11 | `11 mod 10` |      1 |
|  21 | `21 mod 10` |      1 |
|   2 | `2 mod 10`  |      2 |

Key `1`, `11`, dan `21` menghasilkan indeks yang sama, yaitu indeks `1`.

Kondisi tersebut disebut **collision**.

---

## 5. Method `insert()`

```java
void insert(int key, String value) {
    int index = hash(key);
    Node baru = new Node(key, value);

    if (table[index] == null) {
        table[index] = baru;
    } else {
        Node bantu = table[index];

        while (bantu.next != null) {
            bantu = bantu.next;
        }

        bantu.next = baru;
    }
}
```

Method `insert()` digunakan untuk memasukkan pasangan `key` dan `value` ke dalam Hash Table.

### Menentukan indeks

```java
int index = hash(key);
```

Program memanggil method `hash()` untuk menentukan indeks penyimpanan.

### Membuat Node baru

```java
Node baru = new Node(key, value);
```

Program membuat Node yang berisi key dan value yang akan dimasukkan.

### Memasukkan data ke indeks kosong

```java
if (table[index] == null) {
    table[index] = baru;
}
```

Jika indeks masih kosong, Node baru langsung dimasukkan ke indeks tersebut.

### Menangani collision

```java
else {
    Node bantu = table[index];

    while (bantu.next != null) {
        bantu = bantu.next;
    }

    bantu.next = baru;
}
```

Jika indeks sudah berisi data, program menelusuri Linked List sampai Node terakhir.

Node baru kemudian ditambahkan pada bagian akhir Linked List.

Contoh:

```text
Indeks 1 : [1, Apel] -> [11, Jeruk] -> [21, Mangga] -> null
```

---

## 6. Method `search()`

```java
String search(int key) {
    int index = hash(key);
    Node bantu = table[index];

    while (bantu != null) {
        if (bantu.key == key) {
            return bantu.value;
        }

        bantu = bantu.next;
    }

    return "Tidak ditemukan";
}
```

Method `search()` digunakan untuk mencari value berdasarkan key.

Program terlebih dahulu menentukan indeks:

```java
int index = hash(key);
```

Kemudian program memeriksa setiap Node pada Linked List:

```java
while (bantu != null)
```

Jika key ditemukan:

```java
if (bantu.key == key) {
    return bantu.value;
}
```

Program mengembalikan value dari key tersebut.

Jika key tidak ditemukan, program mengembalikan:

```text
Tidak ditemukan
```

---

## 7. Method `remove()`

```java
void remove(int key) {
    int index = hash(key);

    Node bantu = table[index];
    Node sebelumnya = null;

    while (bantu != null) {
        if (bantu.key == key) {
            if (sebelumnya == null) {
                table[index] = bantu.next;
            } else {
                sebelumnya.next = bantu.next;
            }

            System.out.println(
                "Data dengan key " + key + " berhasil dihapus"
            );

            return;
        }

        sebelumnya = bantu;
        bantu = bantu.next;
    }

    System.out.println(
        "Data dengan key " + key + " tidak ditemukan"
    );
}
```

Method `remove()` digunakan untuk menghapus data berdasarkan key.

Program menggunakan dua variabel:

```java
Node bantu = table[index];
Node sebelumnya = null;
```

* `bantu` digunakan untuk menunjuk Node yang sedang diperiksa.
* `sebelumnya` digunakan untuk menyimpan Node sebelum Node yang sedang diperiksa.

### Menghapus Node pertama

```java
if (sebelumnya == null) {
    table[index] = bantu.next;
}
```

Jika `sebelumnya` masih bernilai `null`, berarti data yang dihapus berada pada Node pertama.

### Menghapus Node tengah atau terakhir

```java
else {
    sebelumnya.next = bantu.next;
}
```

Node sebelumnya akan diarahkan ke Node setelah Node yang dihapus.

---

## 8. Method `display()`

```java
void display() {
    for (int i = 0; i < 10; i++) {
        System.out.print("Indeks " + i + " : ");

        Node bantu = table[i];

        while (bantu != null) {
            System.out.print(
                "[" + bantu.key + ", " + bantu.value + "] -> "
            );

            bantu = bantu.next;
        }

        System.out.println("null");
    }
}
```

Method `display()` digunakan untuk menampilkan seluruh isi Hash Table.

Perulangan berikut digunakan untuk mengakses indeks `0` sampai `9`:

```java
for (int i = 0; i < 10; i++)
```

Setiap Linked List ditelusuri menggunakan:

```java
while (bantu != null)
```

Format data yang ditampilkan adalah:

```text
[key, value] -> [key, value] -> null
```

---

# Pengujian Program

## Test Case 1: Insert Tanpa Collision

Test case pertama digunakan untuk menguji proses memasukkan data yang menghasilkan indeks berbeda.

### Kode pengujian

```java
System.out.println("=================================");
System.out.println("TEST CASE 1: INSERT TANPA COLLISION");
System.out.println("=================================");

data.insert(1, "Apel");
data.insert(2, "Pisang");
data.insert(3, "Melon");

data.display();
```

### Data yang dimasukkan

| Key | Value  |
| --: | ------ |
|   1 | Apel   |
|   2 | Pisang |
|   3 | Melon  |

### Perhitungan indeks

| Key | Perhitungan | Indeks |
| --: | ----------- | -----: |
|   1 | `1 mod 10`  |      1 |
|   2 | `2 mod 10`  |      2 |
|   3 | `3 mod 10`  |      3 |

Ketiga key menghasilkan indeks yang berbeda sehingga tidak terjadi collision.

### Hasil yang diharapkan

```text
