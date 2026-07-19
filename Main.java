public class Main {

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

    static class HashTable {
        Node[] table = new Node[10];

        int hash(int key) {
            return key % 10;
        }

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

                    System.out.println("Data berhasil dihapus");
                    return;
                }

                sebelumnya = bantu;
                bantu = bantu.next;
            }

            System.out.println("Data tidak ditemukan");
        }

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
    }

    public static void main(String[] args) {
        HashTable data = new HashTable();

        data.insert(11, "Jeruk");
        data.insert(21, "Manggga");
        data.insert(12, "Anggur");
        data.insert(22, "Semangka");

        System.out.println("Isi Hash Table:");
        data.display();

        System.out.println("\nPencarian key 11:");
        System.out.println(data.search(11));

        System.out.println("\nMenghapus key 21:");
        data.remove(21);

        System.out.println("\nIsi setelah dihapus:");
        data.display();
    }
}