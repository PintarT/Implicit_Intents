# Implicit Intents - Android Aplikacija

Aplikacija **Implicit Intents** je demonstracijski projekt v jeziku **Java** za sistem Android, ki prikazuje uporabo neposrednih (implicitnih) namenov (*implicit intents*). Aplikacija omogoča interakcijo z drugimi sistemskimi in nameščenimi aplikacijami na napravi Android.

---

## 📱 Funkcionalnosti

Aplikacija omogoča štiri glavne akcije:

1. **Odpiranje spletne strani** (`Intent.ACTION_VIEW`)
   - Vnesite URL spletne strani (npr. `google.com` ali `https://github.com`).
   - Aplikacija samodejno preveri format in odpre privzeti spletni brskalnik.

2. **Prikaz lokacije na zemljevidu** (`Intent.ACTION_VIEW`)
   - Vnesite ime mesta, naslov ali geografske koordinate.
   - Odpre se aplikacija za zemljevide (npr. Google Maps) s prikazano lokacijo (`geo:0,0?q=...`).

3. **Deljenje besedila** (`Intent.ACTION_SEND`)
   - Vnesite poljubno besedilo.
   - Odpre se sistemski izbirnik (*Chooser*), ki omogoča deljenje besedila prek e-pošte, sporočil ali družbenih omrežij.

4. **Klicanje telefonske številke** (`Intent.ACTION_DIAL`)
   - Vnesite telefonsko številko.
   - Odpre se sistemski klicatelj (*Dialer*) z vneseno številko, pripravljeno za klic.

---

## 🛠️ Uporabljene tehnologije

- **Jezik:** Java
- **UI:** XML Layouts (`LinearLayout`, `EditText`, `Button`, `TextView`)
- **Android SDK:** Min SDK 24+, Target SDK 34+
- **Knjižnice:** AndroidX AppCompat, Material Components, EdgeToEdge

---

## 💡 Primeri kode (Implicit Intents)

```java
// 1. Odpiranje spletne strani
Uri webpage = Uri.parse("https://www.google.com");
Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
startActivity(intent);

// 2. Prikaz lokacije
Uri addressUri = Uri.parse("geo:0,0?q=" + Uri.encode("Ljubljana"));
Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
startActivity(intent);

// 3. Deljenje besedila
Intent intent = new Intent(Intent.ACTION_SEND);
intent.setType("text/plain");
intent.putExtra(Intent.EXTRA_TEXT, "Pozdrav iz aplikacije!");
startActivity(Intent.createChooser(intent, "Deli besedilo z:"));

// 4. Klicanje številke
Uri phoneUri = Uri.parse("tel:012345678");
Intent intent = new Intent(Intent.ACTION_DIAL, phoneUri);
startActivity(intent);
```

---

## 🚀 Namestitev in zagon

1. Klonsko kopirajte repozitorij:
   ```bash
   git clone https://github.com/UPORABNIŠKO_IME/Implicit_Intents.git
   ```
2. Odprite projekt v **Android Studio**.
3. Počakajte, da se zaključi sinhrinizacija z **Gradle**.
4. Zaženite aplikacijo na emulatorju ali fizični Android napravi.

---

## 📄 Licenca

Projekt je odprtokoden in na voljo pod licenco [MIT](LICENSE).
