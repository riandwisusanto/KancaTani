package com.example.kancatani.Model

object db_alamat {

    val provinsi = arrayOf(
        "x", "Banten", "Daerah Khusus Ibukota Jakarta", "Daerah Istimewa Yogyakarta", "Jawa Barat", "Jawa Tengah", "Jawa Timur"
    )

    val kota = arrayOf(
        alamatmodel("x", arrayOf("x")),
        alamatmodel(
            "Banten",
            arrayOf("Kabupaten Lebak", "Kabupaten Pandeglang", "Kabupaten Serang", "Kabupaten Tangerang", "Kota Cilegon", "	Kota Serang",
                "Kota Tangerang", "Kota Tangerang Selatan")
        ),
        alamatmodel(
            "Daerah Khusus Ibukota Jakarta",
            arrayOf("Kabupaten Kepulauan Seribu", "Jakarta Barat", "Jakarta Pusat", "Jakarta Selatan", "Jakarta Timur", "Jakarta Utara")
        ),
        alamatmodel(
            "Daerah Istimewa Yogyakarta",
            arrayOf("Kabupaten Bantul", "Kabupaten Gunungkidul", "Kabupaten Kulon Progo", "Kabupaten Sleman", "Kota Yogyakarta")
        ),
        alamatmodel(
            "Jawa Barat",
            arrayOf("Kabupaten Bandung", "Kabupaten Bandung Barat", "Kabupaten Bekasi", "Kabupaten Bogor", "Kabupaten Ciamis", "Kabupaten Cianjur", "Kabupaten Cirebon",
                "Kabupaten Garut", "Kabupaten Indramayu", "Kabupaten Karawang", "Kabupaten Kuningan", "Kabupaten Majalengka", "Kabupaten Pangandaran", "Kabupaten Purwakarta",
                "Kabupaten Subang", "Kabupaten Sukabumi", "Kabupaten Sumedang", "Kabupaten Tasikmalaya", "Kota Bandung", "Kota Banjar", "Kota Bekasi", "Kota Bogor",
                "Kota Cimahi", "Kota Cirebon", "Kota Depok", "Kota Sukabumi", "Kota Tasikmalaya")
        ),
        alamatmodel(
            "Jawa Tengah",
            arrayOf("Kabupaten Banjarnegara", "Kabupaten Banyumas", "Kabupaten Batang", "Kabupaten Blora", "Kabupaten Boyolali", "Kabupaten Brebes", "Kabupaten Cilacap",
                "Kabupaten Demak", "Kabupaten Grobogan", "Kabupaten Jepara", "Kabupaten Karanganyar", "Kabupaten Kebumen", "Kabupaten Kendal", "Kabupaten Klaten",
                "Kabupaten Kudus", "Kabupaten Magelang", "Kabupaten Pati", "Kabupaten Pekalongan", "Kabupaten Pemalang", "Kabupaten Purbalingga", "Kabupaten Purworejo",
                "Kabupaten Rembang", "Kabupaten Semarang", "Kabupaten Sragen", "Kabupaten Sukoharjo", "Kabupaten Tegal", "Kabupaten Temanggung", "Kabupaten Wonogiri",
                "Kabupaten Wonosobo", "Kota Magelang", "Kota Pekalongan", "Kota Salatiga", "Kota Semarang", "Kota Surakarta", "Kota Tegal")
        ),
        alamatmodel(
            "Jawa Timur",
            arrayOf("Kabupaten Bangkalan", "Kabupaten Banyuwangi", "Kabupaten Blitar", "Kabupaten Bojonegoro", "Kabupaten Bondowoso", "Kabupaten Gresik", "Kabupaten Jember",
                "Kabupaten Jombang", "Kabupaten Kediri", "Kabupaten Lamongan", "Kabupaten Lumajang", "Kabupaten Madiun", "Kabupaten Magetan", "Kabupaten Malang",
                "Kabupaten Mojokerto", "Kabupaten Nganjuk", "Kabupaten Ngawi", "Kabupaten Pacitan", "Kabupaten Pamekasan", "Kabupaten Pasuruan", "Kabupaten Ponorogo",
                "Kabupaten Probolinggo", "Kabupaten Sampang", "Kabupaten Sidoarjo", "Kabupaten Situbondo", "Kabupaten Sumenep", "Kabupaten Trenggalek", "Kabupaten Tuban",
                "Kabupaten Tulungagung", "Kota Batu", "Kota Blitar", "Kota Kediri", "Kota Madiun", "Kota Malang", "Kota Mojokerto", "Kota Pasuruan",
                "Kota Probolinggo", "Kota Surabaya")
        )
    )

    val kecamatan = arrayOf(
        alamatmodel("x", arrayOf("x")),
        alamatmodel(
            "Kabupaten Ponorogo",
            arrayOf("Sooko", "Pudak", "Pulung", "Babadan", "Badegan", "Balong", "Jambon", "Jenangan", "Jetis", "Kauman", "Mlarak", "Ngebel", "Ngrayun", "Ponorogo",
                "Sambit", "Sampung", "Sawoo", "Siman", "Slahung", "Sukorejo")
        ),
        alamatmodel(
            "Kabupaten Madiun",
            arrayOf("Balerejo", "Dagangan", "Dolopo", "Geger", "Gemarang", "Jiwan", "Kare", "Kebonsari", "Madiun", "Mejayan", "Pilangkenceng",
                "Saradan", "Sawahan", "Wonosari", "Wungu")
        ),
        alamatmodel(
            "Kota Madiun",
            arrayOf("Kartoharjo", "Mangunharjo", "Taman")
        ),
        alamatmodel(
            "Kota Surabaya",
            arrayOf("Jambangan", "Benowo", "Asemrowo", "Bubutan", "Bulak", "Dukuh Pakis", "Gayungan", "Genteng", "Gubeng", "Gununganyar", "Karangpilang",
                "Kenjeran", "Krembangan", "Lakar Santri", "Mulyerejo", "Pabean Cantik", "Pakal", "Rungkut", "Sambikerep", "Sawahan", "Semampir",
                "Simokerto", "Sukolilo", "Sukomanunggal", "Tambaksari", "Tandes", "Tegalsari", "Tenggelis Mejoyo", "Wiyung", "Wonocolo", "Wonokromo")
        ),
        alamatmodel(
            "Kota Bandung",
            arrayOf("Sukasari", "Andir", "Sukajadi", "Cicendo", "Cidadap", "Coblong", "Bandung Wetan", "Sumur Bandung", "Cibeunying Kaler", "Cibeunying Kidul",
                "Kiaracondong", "Batununggal", "Lengkong", "Regol", "Astanaanyar", "Bojongloa Kaler", "Babakan Ciparay", "Bojongloa Kidul", "Bandung Kulon",
                "Antapani", "Aracamanik", "Ujungberung", "Cibiru", "Rancasari", "Buah Batu", "Bandung Kidul", "Panyileukan", "Cinambo", "Mandala Jati", "Gede Bage")
        ),
        alamatmodel(
            "Kabupaten Lebak",
            arrayOf("Banjarsari", "Bayah", "Bojongmanik", "Cibadak", "Cibeber", "Cigemblong", "Cihara", "Cijaku", "Cikulur", "Cileles", "Cilograng", "Cimarga",
                "Cipanas", "Cirinten", "Cirugbitung", "Gunungkencana", "Kalang Anyar", "Lebak Gedong", "Leuwidimar", "Maja", "Malingping", "Muncang", "Panggarangan",
                "Rangkasbitung", "Sajira", "Sobang", "Wanasalam", "Warunggunung")
        ),
        alamatmodel(
            "Kabupaten Pandeglang",
            arrayOf("Angsana", "Banjar", "Bojong", "Cadasari", "Carita", "Cibaliung", "Cibitung", "Cigeulis", "Cikedal", "Cikeusik", "Cimanggu", "Cimanuk", "Cipeucang", "Cisata",
                "Jiput", "Kaduhejo", "Karang Tanjung", "Koroncong", "Labuan", "Majasari", "Mandalawangi", "Mekarjaya", "Menes", "Munjul", "Pagelaran", "Pandeglang", "Patia",
                "Panimbang", "Picung", "Pulosari", "Saketi", "Sindangresmi", "Sobang", "Sukaresmi", "Sumur")
        ),
        alamatmodel(
            "Kabupaten Serang",
            arrayOf("Anyar", "Bandung", "Baros", "Binuang", "Bojonegara", "Carenang", "	Cikande", "Cikeusal", "Cinangka", "Ciomas", "Ciruas", "Gunung Sari", "Jawilan", "Kibin",
                "Kopo", "Kragilan", "Kramatwatu", "Lebak Wangi", "Mancak", "Pabuaran", "Padarincang", "Pamarayan", "Petir", "Pontang", "Pulo Ampel", "Tanara", "Tirtayasa",
                "Tunjung Teja", "Waringinkurung")
        ),
        alamatmodel(
            "Kabupaten Tangerang",
            arrayOf("Balaraja", "Cikupa", "Cisauk", "Cisoka", "Curug", "Gunung Kaler", "Jambe", "Jayanti", "Kelapa Dua", "Kemiri", "Kresek", "Kronjo", "Kosambi", "Legok", "Mauk",
                "Mekarbaru", "Pagedangan", "Pakuhaji", "Panongan", "Pasar Kemis", "Rajeg", "Sepatan", "Sepatan Timur", "Sindang Jaya", "Solear", "Sukadiri", "Sukamulya",
                "Teluknaga", "Tigakarsa")
        ),
        alamatmodel(
            "Kota Cilegon",
            arrayOf("Cibeber", "Cilegon", "Citangkil", "Ciwandan", "Gerogol", "Jombang", "Pulo Merak", "Purwakarta")
        ),
        alamatmodel(
            "Kota Serang",
            arrayOf("Cipojok Jaya", "Curug", "Kasemen", "Serang", "Taktakan", "Walantaka")
        ),
        alamatmodel(
            "Kota Tangerang",
            arrayOf("Batuceper", "Benda", "Cibodas", "Ciledug", "Cipondoh", "Jatiuwung", "Karangtengah", "Karawaci", "Larangan", "Neglasari", "Periuk", "Pinang", "Tangerang")
        ),
        alamatmodel(
            "Kota Tangerang Selatan",
            arrayOf("Ciputat", "Ciputat Timur", "Pamulang", "Pondok Aren", "Serpong", "Serpong Utara", "Setu")
        ),
        alamatmodel(
            "Kabupateng Kepulauan Seribu",
            arrayOf("Kepulauan Seribu Utara", "Kepulauan Seribu Selatan")
        ),
        alamatmodel(
            "Jakarta Barat",
            arrayOf("Cengkareng", "Grogol Petamburan", "Taman Sari", "Tambora", "Kebon Jeruk", "Kalideres", "Palmerah", "Kembangan")
        ),
        alamatmodel(
            "Jakarta Pusat",
            arrayOf("Cempak Putih", "Gambir", "Johar Baru", "Kemayoran","Menteng", "Sawah Besar", "Senen", "Tanah Abang")
        ),
        alamatmodel(
            "Jakarta Selatan",
            arrayOf("Cilandak", "Jagakarsa", "Kebayoran Baru", "Kebayoran Lama", "Mampang Prapatan", "Pancoran", "Pasar Minggu", "Pesanggrahan", "Setia Budi", "Tebet")
        ),
        alamatmodel(
            "Jakarta Timur",
            arrayOf("Cakung", "Cipayung", "Ciracas", "Duren Sawit", "Jatinegara", "Kramat Jati", "Makasar", "Matraman", "Pasar Rebo", "Pulo Gadung")
        ),
        alamatmodel(
            "Jakarta Utara",
            arrayOf("Cilincing", "Kelapa Gading", "Koja", "Pademangan", "Penjaringan", "Tanjung Priok")
        ),
        alamatmodel(
            "Kabupaten Bantul",
            arrayOf("Banguntapan", "Jetis", "Pleret", "Bambanglipuro", "Sewon", "Imogiri", "Kretek", "Sanden", "Srandakan", "Sedayu", "Pandak", "Pajangan", "Kasihan",
                "Piyungan", "Bantul", "Pundong", "Dlingo")
        ),
        alamatmodel(
            "Kabupaten Gunung Kidul",
            arrayOf("Gedangsari", "Girisubo", "Karangmojo", "Ngawen", "Nglipar", "Paliyan", "Panggang", "Patuk", "Playen", "Ponjong", "Purwosari", "Rongkop", "Saptosari",
                "Semanu", "Semin", "Tajungsari", "Tepus", "Wonosari")
        ),
        alamatmodel(
            "Kabupaten Kulon Progo",
            arrayOf("Galur", "Girimulyo", "Kalibawang", "Kokap", "Lendah", "Nanggulan", "Panjatan", "Pengasih", "Samigaluh", "Sentolo", "Temon", "Wates")
        ),
        alamatmodel(
            "Kabupaten Sleman",
            arrayOf("Berbah", "Cangkring", "Depok", "Gamping", "Godean", "Kalasan", "Minggir", "Mlati", "Moyudan", "Ngaglik", "Ngemplak", "Pakem", "Prambanan", "Seyegan",
                "Sleman", "Tempel", "Turi")
        ),
        alamatmodel(
            "Kota Yogyakarta",
            arrayOf("Danurejan", "Gedongtengen", "Gondokusuman", "Gondomanan", "Jetis", "Kotagede", "Kraton", "Mantrijeron", "Mergangsan", "Ngampilan", "Pakualaman", "Tegalrejo",
                "Umbulharjo", "Wirobrajan")
        ),
        alamatmodel(
            "Kabupaten Bandung",
            arrayOf("Arjasari", "Baleendah", "Banjaran", "Bojongsoang", "Cangkuang", "Cicalengka", "Cikancung", "Cilengkrang", "Cileunyi", "Cimaung", "Cimenyan", "Ciparay", "Ciwidey",
                "Dayuehkolot", "Ibun", "Katapang", "Kertasari", "Kutawaringin", "Majalaya", "Margaasih", "Margahayu", "Nagreg", "Pacet", "Pameungpeuk", "Pangalengan", "Paseh",
                "Pasirjambu", "Rancabali", "Rancaekek", "Solokan Jeruk", "Soreang")
        ),
        alamatmodel(
            "Kabupaten Bandung Barat",
            arrayOf("Batujajar", "Cihampelas", "Cikalong Wetan", "Cililin", "Cipatat", "Cipeundeuy", "Cipongkor", "Cisarua", "Gununghalu", "Lembang", "Ngamprah", "Parongpong", "Rongga",
                "Saguling", "Sindangkerta")
        ),
        alamatmodel(
            "Kabupaten Bekasi",
            arrayOf("Babelan", "Bojongmangu", "Cabangbungin", "Cibarusah", "Cibitung", "Cikarang Barat", "Cikarang Pusat", "Cikarang Selatan", "Cikarang Timur", "Cikarang Utara",
                "Karang Bahagia", "Kedung Waringin", "Muaragembong", "Pebayuran", "Serangbaru", "Setu", "Sukakarya", "Sukatani", "Sukawangi", "Tambelang", "Tambun Selatan",
                "Tambun Utara", "Tarumajaya")
        ),
        alamatmodel(
            "Kabupaten Bogor",
            arrayOf("Babakan Madang", "Bojong Gede", "Caringin", "Cariu", "Ciampea", "Ciawi", "Cibinong", "Cibungbulang", "Cigombong", "Cigudeg", "Cijeruk", "Cileungsi", "Ciomas",
                "Cisarua", "Ciseeng", "Citeureup", "Dramaga", "Gunung Putri", "Gunung Sindur", "Jasinga", "Jonggol", "Kemang", "Klapanunggal", "Leuwiliang", "Leuwisadeng",
                "Megamendung", "Nanggung", "Pamijahan", "Parung", "Parung Panjang", "Ranca Bungur", "Rumpin", "Sukajaya", "Sukamakmur", "Sukaraja", "Tajurhalang", "Tamansari",
                "Tanjungsari", "Tenjo", "Tenjolya")
        ),
        alamatmodel(
            "Kabupaten Ciamis",
            arrayOf("Banjaranyar", "Banjarsari", "Baregbeg", "Ciamis", "Cidolog", "Ciharbeuti", "Cijeungjing", "Cikoneng", "Cimaragas", "Cipaku", "Cisaga", "Jatinagara", "Kawali",
                "Lakbok", "Lumbung", "Pamarican", "Panjalu", "Panwangan", "Panumbangan", "Purwadadi", "Rajadesa", "Rancah", "Sadananya", "Sindangkasih", "Sukadana", "Sukamantri",
                "Tambaksari")
        ),
        alamatmodel(
            "Kabupaten Cianjur",
            arrayOf("Agrabinta", "Bojongpicung", "Campaka", "Campaka Mulya", "Cianjur", "Cibeber", "Cibinong", "Cidaun", "Cijati", "Cikadu", "Cikalongkulon", "Cilaku", "Cipanas",
                "Ciranjang", "Cugenang", "Gekbrong", "Haurwangi", "Kadupandak", "Karang Tengah", "Leles", "Mande", "Naringgul", "Pacet", "Pagelaran", "Pasirkuda", "Sindangbarang",
                "Sukaluyu", "Sukanagara", "Sukaresmi", "Takokak", "Tanggeung", "Warungkondang")
        ),
        alamatmodel(
            "Kabupaten Cirebon",
            arrayOf("Arjawinangun", "Astanajapura", "Babakan", "Beber", "Ciledug", "Ciwaringin", "Depok", "Dukupuntang", "Gebang", "Gegesik", "Gempol", "Greget", "Gunung Jati",
                "Jamblang", "Kaliwedi", "Kapetakan", "Karangsembung", "Karangwareng", "Kedawung", "Klangenan", "Lemahabang", "Losari", "Mundu", "Pabedilan", "Pabuaran", "Palimanan",
                "Pangenan", "Panguragan", "Pasaleman", "Plered", "Plumbon", "Sedong", "Sumber", "Suranenggala", "Susukan", "Lebak", "Talun", "Tengah Tani", "Waled", "Weru")
        ),
        alamatmodel(
            "Kabupaten Garut",
            arrayOf("Banjarwangi", "Banyuresmi", "Bayongbong", "Blubur Limbangan", "Bungbulang", "Caringin", "Cibalong", "Cibatu", "Cibiuk", "Cigedug", "Cihurip", "Cikajang", "Cikelet",
                "Cilawu", "Cisewu", "Cisompet", "Cisurupan", "Garut Kota", "Kadungora", "Karangpawitan", "Karangtengah", "Kersamanah", "Leles", "Leuwigoong", "Malangbong", "Mekarmukti",
                "Pakenjeng", "Pameungpeuk", "Pamulihan", "Pangatikan", "Pasirwangi", "Peundeuy", "Samarang", "Selaawi", "Singajaya", "Sucinaraja", "Sukaresmi", "Sukawening", "Talegong",
                "Tarogong Kaler", "Tarogong Kidul", "Wanaraja")
        ),
        alamatmodel(
            "Kabupateng Indramayu",
            arrayOf("Anjatan", "Arahan", "Balongan", "Bangodua", "Bongas", "Cantigi", "Cikedung", "Gabuswetan", "Gantar", "Haurgeulis", "Indramayu", "Jatibarang", "Juntinyuat", "Kandanghaur",
                "Karangampel", "Kedokan Bunder", "Kertasemaya", "Krangkeng", "Kroya", "Lelea", "Lohbener", "Losarang", "Pasekan", "Patrol", "Sindang", "Sliyeg", "Sukagumiwang", "Sukra",
                "Trisi", "Tukdana", "Widasari")
        ),
        alamatmodel(
            "Kabupaten Karawang",
            arrayOf("Banyusari", "Batujaya", "Ciampel", "Cibuaya", "Cikampek", "Cilamaya Kulon", "Cilamaya Wetan", "Cilebar", "Jatisari", "Jayakerta", "Karawang Barat", "Karawang Timur",
                "Klari", "Kota Baru", "Kutawaluya", "Lemahabang", "Majalaya", "Pakisjaya", "Pangkalan", "Pedes", "Purwasari", "Rawamerta", "Rengasdengklok", "Tegalwaru", "Telagasari",
                "Telukjambe Barat", "Telukjambe Timur", "Tempuran", "Tirtajaya", "Tirtamulya")
        ),
        alamatmodel(
            "Kabupateng Kuningan",
            arrayOf("Ciawigebang", "Cibeureum", "Cibingbin", "Cidahu", "Cigandamekar", "Cigugur", "Cilebak", "Cilimus", "Cimahi", "Ciniru", "Cipicung", "Ciwaru", "Darma", "Garawangi",
                "Hantara", "Jalaksana", "Japara", "Kadugede", "Kalimanggis", "Karangkancana", "Kramatmulya", "Kuningan", "Lebakwangi", "Luragung", "Maleber", "Mandirancan", "Nusaherang",
                "Pancalang", "Pasawahan", "Selajambe", "Sindangagung", "Subang")
        ),
        alamatmodel(
            "Kabupaten Kuningan",
            arrayOf("Argapura", "Banjaran", "Bantarujeg", "Cigasong", "Cikijing", "Cingambul", "Dawuan", "Jatitujuh", "Jatiwangi", "Kadipaten", "Kasokandel", "Kertajati", "Lemahsugih",
                "Leuwimunding", "Ligung", "Maja", "Majalengka", "Malausma", "Palasah", "Panyingkiran", "Rajagaluh", "Sindang", "Sindangwangi", "Sukahaji", "Sumberjaya", "Talaga")
        ),
        alamatmodel(
            "Kabupaten Pangandaran",
            arrayOf("Cigugur", "Cijulang", "Cimerak", "Kalipucang", "Langkaplancar", "Mangunjaya", "Padaherang", "Pangandaran", "Parigi", "Sidamulih")
        ),
        alamatmodel(
            "Kabupaten Purwakarta",
            arrayOf("Babakancikao", "Bojong", "Bungursari", "Campaka", "Cibatu", "Darangdan", "Jatiluhur", "Kiarapedes", "Maniis", "Pasawahan", "Plered", "Pondoksalam", "Purwakarta",
                "Sukasari", "Sukatani", "Tegalwaru", "Wanayasa")
        ),
        alamatmodel(
            "Kabupaten Subang",
            arrayOf("Binong", "Blanakan", "Ciasem", "Ciater", "Cibogo", "Cijambe", "Cikaum", "Cipeundeuy", "Cipunagara", "Cisalak", "Compreng", "Dawuan", "Jalan Cagak", "Kalijati",
                "Kasomalang", "Legon Kulon", "Pabuaran", "Pagaden", "Pagaden Barat", "Pamanukan", "Patok Beusi", "Purwadadi", "Pusakajaya", "Pusakanagara", "Sagalaherang",
                "Subang", "Serangpanjang", "Sukasari", "Tambakdahan", "Tanjungsiang")
        ),
        alamatmodel(
            "Kabupaten Sukabumi",
            arrayOf("Bantargadung", "Bojong Genteng", "Caringin", "Ciambar", "Cibadak", "Cibitung", "Cicantayan", "Cicurug", "Cidadap", "Cidahu", "Cidolog", "Ciemas", "Cikakak",
                "Cikembar", "Cikidang", "Cimanggu", "Ciracap", "Cireunghas", "Cisaat", "Cisolok", "Curugkembar", "Geger Bitung", "Gunung Guruh", "Jampang Kulon", "Jampang Tengah",
                "Kabandungan", "Kadudampit", "Kalapa Nunggal", "Kali Bunder", "Kebonpedes", "Lengkong", "Nagrak", "Nyalindung", "Pabuaran", "Parakan Salak", "Parung Kuda",
                "Pelabuhan Ratu", "Purabaya", "Sagaranten", "Simpenan", "Sukabumi", "Sukalarang", "Sukaraja", "Surade", "Tegal Buleud", "Waluran", "Warung Kiara")
        ),
        alamatmodel(
            "Kabupaten Sumedang",
            arrayOf("Buahdua", "Cibugel", "Cimalaka", "Cimanggung", "Cisarua", "Cisitu", "Conggeang", "Darmaraja", "Ganeas", "Jatigede", "Jatinangor", "Jatinunggal", "Pamulihan",
                "Paseh", "Rancakalong", "Situraja", "Sukasari", "Sumedang Selatan", "Sumedang Utara", "Surian", "Tanjungkerta", "Tanjungmedar", "Tanjungsari", "Tomo", "Wado",
                "Ujung Jaya")
        ),
        alamatmodel(
            "Kabupaten Tasikmalaya",
            arrayOf("Bantarkalong", "Bojong Asih", "Bojonggambir", "Ciawi", "Cibalong", "Cigalontang", "Cikalong", "Cikatomas", "Cineam", "Cipatujah", "Cisayong", "Culamega",
                "Gunungtanjung", "Jamanis", "Jatiwaras", "Kadipaten", "Karang Jaya", "Karangnunggal", "Leuwisari", "Mangunreja", "Manonjaya", "Padakembang", "Pagerageung",
                "Pancatengah", "Parungponteng", "Puspahiang", "Rajapolah", "Salawu", "Salopa", "Sariwangi", "Singaparna", "Sodonghilir", "Sukahening", "Sukaraja", "Sukarame",
                "Sukaratu", "Sukaresik", "Tanjungjaya", "Taraju")
        ),
        alamatmodel(
            "Kota Banjar",
            arrayOf("Banjar", "Langensari", "Pataruman", "Purwaharja")
        ),
        alamatmodel(
            "Kota Bekasi",
            arrayOf("Bantargebang", "Bekasi Barat", "Bekasi Selatan", "Bekasi Timur", "Bekasi Utara", "Jatiasih", "Jatisampurna", "Medan Satria", "Mustikajaya", "Pondok Gede",
                "Pondok Melati", "Rawalumbu")
        ),
        alamatmodel(
            "Kota Bogor",
            arrayOf("Bogor Barat", "Bogor Selatan", "Bogor Tengah", "Bogor Timur", "Bogor Utara", "Tanah Sareal")
        ),
        alamatmodel(
            "Kota Cimahi",
            arrayOf("Cimahi Selatan", "Cimahi Tengah", "Cimahi Utara")
        ),
        alamatmodel(
            "Kota Cirebon",
            arrayOf("Hajarmukti", "Kejaksan", "Kesambi", "Lemahwungkuk", "Pekalipun")
        ),
        alamatmodel(
            "Kota Depok",
            arrayOf("Beji", "Bojongsari", "Cilodong", "Cimanggis", "Cinere", "Cipayung", "Limo", "Pancoran Mas", "Sawangan", "Sukmajaya", "Tapos")
        ),
        alamatmodel(
            "Kota Sukabumi",
            arrayOf("Baros", "Cibeureum", "Cikole", "Citamiang", "Gunungpuyuh", "Lembursitu", "Warudoyong")
        ),
        alamatmodel(
            "Kota Tasikmalaya",
            arrayOf("Bungursari", "Cibeureum", "Cihideung", "Cipedes", "Indihiang", "Kawalu", "Mangkubumi", "Purbaratu", "Tamansari", "Tawang")
        ),
        alamatmodel(
            "Kabupaten Banjarnegara",
            arrayOf("Banjarmangu", "Banjarnegara", "Batur", "Bawang", "Kalibening", "Karangkobar", "Madukara", "Mandiraja", "Pagedongan", "Pagentan", "Pandanarum", "Pejawaran",
                "Punggelan", "Purwonegoro", "Purworejo Klampok", "Rakit", "Sigaluh", "Susukan", "Wanadadi  Wonodadi", "Wanayasa")
        ),
        alamatmodel(
            "Kabupaten Banyumas",
            arrayOf("Lumbir", "Wangon", "Jatilawang", "Rawalo", "Kebasen", "Kemranjen", "Sumpiuh", "Tambak", "Somagede", "Kalibagor", "Banyumas", "Patikraja", "Purwojati",
                "Ajibarang", "Gumelar", "Pekuncen", "Cilongok", "Karanglewas", "Kedungbanteng", "Baturraden", "Sumbang", "Kembaran", "Sokaraja", "Purwokerto Selatan",
                "Purwokerto Barat", "Purwokerto Timur", "Purwokerto Utara")
        ),
        alamatmodel(
            "Kabupaten Batang",
            arrayOf("Bandar", "Banyuputih", "Batang", "Bawang", "Blado", "Gringsing", "Kandeman", "Limpung", "Pecalungan", "Reban", "Subah", "Tersono", "Tulis", "Warungasem",
                "Wonotunggal")
        ),
        alamatmodel(
            "Kabupaten Blora",
            arrayOf("Banjarejo", "Blora", "Bogorejo", "Cepu", "Japah", "Jati", "Jepon", "Jiken", "Kedungtuban", "Kradenan", "Kunduran", "Ngawen", "Randublatung", "Sambong",
                "Todanan", "Tunjungan")
        ),
        alamatmodel(
            "Kabupaten Boyolali",
            arrayOf("Ampel", "Andong", "Banyudono", "Boyolali", "Cepogo", "Juwangi", "Karanggede", "Kemusu", "Klego", "Mojosongo", "Musuk", "Ngemplak", "Nogosari", "Sambi", "awit",
                "Selo", "Simo", "Teras", "Wonosegoro")
        ),
        alamatmodel(
            "Kabupaten Brebes",
            arrayOf("Banjarharjo", "Bantarkawung", "Brebes", "Bulakamba", "Bumiayu", "Jatibarang", "Kersana", "Ketanggungan", "Larangan", "Losari", "Paguyangan", "Salem", "Sirampog",
                "Songgom", "Tanjung", "Tonjong", "Wanasari")
        ),
        alamatmodel(
            "Kabupaten Cilacap",
            arrayOf("Adipala", "Bantarsari", "Binangun", "Cilacap Selatan", "Cilacap Tengah", "Cilacap Utara", "Cimanggu", "Cipari", "Dayeuhluhur", "Gandrungmangu", "Jeruklegi",
                "Kampung Laut", "Karangpucung", "Kawunganten", "Kedungreja", "Kesugihan", "Kroya", "Majenang", "Maos", "Nusawungu", "Patimuan", "Sampang", "Sidareja", "Wanareja")
        ),
        alamatmodel(
            "Kabupaten Demak",
            arrayOf("Bonang", "Demak", "Dempet", "Gajah", "Guntur", "Karanganyar", "Karangawen", "Karangtengah", "Kebonagung", "Mijen", "Mranggen", "Sayung", "Wedung", "Wonosalam")
        ),
        alamatmodel(
            "Kabupaten Grobogan",
            arrayOf("Brati", "Gabus", "Geyer", "Godong", "Grobogan", "Gubug", "Karangrayung", "Kedungjati", "Klambu", "Kradenan", "Ngaringan", "Penawangan", "Pulokulon", "Purwodadi",
                "Tanggungharjo", "Tawangharjo", "Tegowanu", "Toroh", "Wirosari")
        ),
        alamatmodel(
            "Kabupaten Jepara",
            arrayOf("Bangsri", "Batealit", "Donorojo", "Jepara", "Kalinyamatan", "Karimunjawa", "Kedung", "Keling", "Kembang", "Mayong", "Mlonggo", "Nalumsari", "Pakis Aji", "Pecangaan",
                "Tahunan", "Welahan")
        ),
        alamatmodel(
            "Kabupaten Karanganyar",
            arrayOf("Colomadu", "Gondangrejo", "Jaten", "Jatipuro", "Jatiyoso", "Jenawi", "Jumantono", "Jumapolo", "Karanganyar", "Karangpandan", "Kebakkramat", "Kerjo", "Matesih",
                "Mojogedang", "Ngargoyoso", "Tasikmadu", "Tawangmangu")
        ),
        alamatmodel(
            "Kabupaten Kebumen",
            arrayOf("Adimulyo", "Aliyan", "Ambal", "Ayah", "Bonorowo", "Buayan", "Buluspesantren", "Gombong", "Karanganyar", "Karanggayam", "Karangsambung", "Kebumen", "Klirong",
                "Kutowinangun", "Kuwarasan", "Mirit", "Padureso", "Pejagoan", "Petanahan", "Poncowarno", "Puring", "Rowokele", "Sadang", "Sempor", "Sruweng", "Prembun")
        ),
        alamatmodel(
            "Kabupaten Kendal",
            arrayOf("Brangsong", "Boja", "Cepiring", "Gemuh", "Kaliwungu Selatan", "Kaliwungu", "Kendal", "Kangkung", "Limbangan", "Ngampel", "Pagerruyung", "Patebon", "Patean",
                "Pegandon", "Plantungan", "Ringinarum", "Rowosari", "Singorojo", "Sukorejo", "Weleri")
        ),
        alamatmodel(
            "Kabupaten Klaten",
            arrayOf("Bayat", "Cawas", "Ceper", "Delanggu", "Gantiwarno", "Jatinom", "Jogonalan", "Juwiring", "Kalikotes", "Karanganom", "Karangdowo", "Karangnongko", "Kebonarum",
                "Kemalang", "Klaten Selatan", "Klaten Tengah", "Klaten Utara", "Manisrenggo", "Ngawen", "Pedan", "Polanharjo", "Prambanan", "Trucuk", "Tulung", "Wedi", "Wonosari")
        ),
        alamatmodel(
            "Kabupaten Kudus",
            arrayOf("Jati", "Undaan", "Mbae", "Gebog", "Kaliwungu", "Kudus", "Mejobo", "Dawe", "Jekulo")
        ),
        alamatmodel(
            "Kabupaten Magelang",
            arrayOf("Bandongan", "Borobudur", "Candimulyo", "Dukun", "Grabag", "Kajoran", "Kaliangkrik", "Mertoyudan", "Mungkid", "Muntilan", "Ngablak", "Ngluwar", "Pakis", "Salam",
                "Salaman", "Sawangan", "Secang", "Srumbung", "Tegalrejo", "Tempuran", "Windusari" )
        ),
        alamatmodel(
            "Kabupaten Pati",
            arrayOf("Batangan", "Cluwak", "Dukuhseti", "Gabus", "Gembong", "Gunungwungkal", "Jaken", "Jakenan", "Juwana", "Kayen", "Margorejo", "Margoyoso", "Pati", "Pucakwangi",
                "Sukolilo", "Tambakromo", "Tayu", "Tlogowungu", "Trangkil", "Wedarijaksa", "Winong", "")
        ),
        alamatmodel(
            "Kabupaten Pekalongan",
            arrayOf("Pekalongan Barat", "Pekalongan Selatan", "Pekalongan Timur", "Pekalongan Utara" )
        ),
        alamatmodel(
            "Kabupaten Pemalang",
            arrayOf("Ampelgading", "Bantarbolang", "Belik", "Bodeh", "Comal", "Moga", "Pemalang", "Petarukan", "Pulosari" ,"Randudongkal", "Taman", "Ulujami", "Warungpring", "Watukumpul")
        ),
        alamatmodel(
            "Kabupaten Purbalingga",
            arrayOf("Bobotsari", "Bojongsari", "Bukateja", "Kaligondang", "Kalimanah", "Karanganyar", "Karangjambu", "Karangmoncol", "Karangreja", "Kejobong", "Kemangkon", "Kertanegara",
                "Kutasari", "Mrebet", "Padamara", "Pengdegan", "Purbalingga", "Rembang")
        ),
        alamatmodel(
            "Kabupaten Purworejo",
            arrayOf("Bagelen", "Banyuurip", "Bayan", "Bener", "Bruno", "Butuh", "Gebang", "Grabag", "Kaligesing", "Kemiri", "Kutoarjo", "Loano", "Ngombol", "Pituruh", "Purwodadi", "Purworejo")
        ),
        alamatmodel(
            "Kabupaten Rembang",
            arrayOf("Bulu", "Gunem", "Kaliori", "Kragan", "Lasem", "Pamotan", "Pancur" ,"Rembang", "Sale", "Sarang", "Sedan", "Sluke", "Sulang", "Sumber")
        ),
        alamatmodel(
            "Kabupaten Semarang",
            arrayOf("Ambarawa", "Bancak", "Bandungan", "Banyubiru", "Bawen", "Bergas", "Bringin", "Getasan", "Jambu", "Kaliwungu", "Pabelan", "Pringapus", "Sumowono", "Suruh", "Susukan", "Tengaran",
                "Tuntang", "Ungaran Barat", "Ungaran Timur")
        ),
        alamatmodel(
            "Kabupaten Sragen",
            arrayOf("Gemolong", "Gesi", "Gondang", "Jenar", "Kalijambe", "Karangmalang", "Kedawung", "Masaran", "Miri", "Mondokan", "Ngampal", "Plupuh", "Sambirejo", "Sambung Macan", "Sidoharjo",
                "Sragen", "Sukodono", "Sumberlawang", "Tangen", "Tanon" )
        ),
        alamatmodel(
            "Kabupaten Sukoharjo",
            arrayOf("Baki", "Bendosari", "Bulu", "Gatak", "Grogol", "Kartasura", "Mojolaban", "Nguter", "Polokarto", "Sukoharjo", "Tawangsari", "Weru")
        ),
        alamatmodel(
            "Kabupaten Tegal",
            arrayOf("Adiwerna", "Balapulang", "Bojong", "Bumijawa", "Dukuhturi", "Dukuhwaru", "Jatinegara", "Kedung Banteng", "Kramat", "Lebaksiu", "Margasari", "Pagerbarang", "Pangkah", "Slawi",
                "Surodadi", "Talang", "Tarub", "Warurejo")
        ),
        alamatmodel(
            "Kabupaten Temanggung",
            arrayOf("Bansari", "Bejen", "Bulu", "Candiroto", "Gemawang", "Jumo", "Kaloran", "Kandangan", "Kedu", "Kledung", "Kranggan", "Ngadirejo", "Parakan", "Pringsurat", "Selopampang",
                "Temanggung", "Tembarak", "Tlogomulyo", "Tretep", "Wonoboyo")
        ),
        alamatmodel(
            "Kabupaten Wonogiri",
            arrayOf("Pracimantoro", "Giritontro", "Giriwoyo", "Batuwarno", "Tirtomoyo", "Nguntoronadi", "Baturetno", "Eromoko", "Wuryantoro", "Manyaran", "Selogiri", "Wonogiri", "Ngadirojo",
                "Sidoharjo", "Jatiroto", "Kismantoro", "Purwantoro", "Bulukerto", "Slogohimo", "Jatisrono", "Jatipurno", "Girimarto", "Karangtengah", "Paranggupito", "Puhpelem")
        ),
        alamatmodel(
            "Kabupaten Wonosobo",
            arrayOf("Garung", "Kalibawang", "Kalikajar", "Kaliwiro", "Kejajar", "Kepil", "Kertek", "Leksono", "Mojotengah", "Sapuran", "Selomerto", "Sukoharjo", "Wadaslintang", "Wonosobo")
        ),
        alamatmodel(
            "Kota Magelang",
            arrayOf("Magelang Selatan", "Magelang Tengah", "Magelang Utara")
        ),
        alamatmodel(
            "Kota Pekalongan",
            arrayOf("Pekalongan Barat", "Pekalongan Selatan", "Pekalongan Timur", "Pekalongan Utara")
        ),
        alamatmodel(
            "Kota Salatiga",
            arrayOf("Argomulyo", "Sidomukti", "Sidorejo", "Tingkir")
        ),
        alamatmodel(
            "Kota Semarang",
            arrayOf("Banyumanik", "Candisari", "Gajah Mungkur", "Gayamsari", "Genuk", "Gunungpati", "Mijen", "Ngaliyan", "Pedurungan", "Semarang Barat", "Semarang Selatan", "Semarang Tengah",
                "Semarang Timur", "Semarang Utara", "Tembalang", "Tugu")
        ),
        alamatmodel(
            "Kota Surakarta",
            arrayOf("Banjarsari", "Jebres", "Laweyan", "Pasar Kliwon", "Serengan")
        ),
        alamatmodel(
            "Kota Tegal",
            arrayOf("Margadana", "Tegal Barat", "Tegal Selatan", "Tegal Timur")
        ),
        alamatmodel(
            "Kabupaten Bangkalan",
            arrayOf("Arosbaya","Bangkalan","Blega","Burneh","Galis","Geger","Kamal","Klampis","Kokop","Konang","Kwanyar","Labang","Modung","Sepulu",
                "Socah","Tanah Merah","Tanjung Bumi","Tragah")
        ),
        alamatmodel(
            "Kabupaten Banyuwangi",
            arrayOf("Pesanggaran","Siliragung","Bangorejo","Purwoharjo","Tegaldlimo","Muncar","Cluring","Gambiran","Tegalsari","Glenmore","Kalibaru",
                "Genteng","Srono","Rogojampi","Kabat","Singojuruh","Sempu","Songgon","Glagah","Licin","Banyuwangi","Giri","Kalipuro","Wongsorejo")
        ),
        alamatmodel(
            "Kabupaten Blitar",
            arrayOf("Bakung","Binangun","Doko","Gandusari","Garum","Kademangan","Kanigoro","Kesamben","Nglegok","Panggungrejo","Ponggok","Sanankulon",
                "Selopuro","Selorejo","Srenggat","Sutojayan","Talun","Udanawu","Wates","Wlingi","Wonodadi","Wonotirto")
        ),
        alamatmodel(
            "Kabupaten Bojonegoro",
            arrayOf("Balen","Baureno","Bojonegoro","Bubulan","Dander","Gayam","Gondang","Kadewan","Kalitidu","Kanor","Kapas","Kasiman","Kedungadem","Kepohbaru",
                "Malo","Margomulyo","Ngambon","Ngasem","Ngraho","Padangan","Purwosari","Sekar","Sugihwaras","Sukosewu","Sumberejo","Tambakrejo","Temayang","Trucuk")
        ),
        alamatmodel(
            "Kabupaten Bondowoso",
            arrayOf("Binakal","Bondowoso","Botolinggo","Cermee","Curahdami","Grujugan","Jambesari Darus Sholah","Klabang","Maesan","Pakem","Prajekan","Pujer","Sempol",
                "Sukosari","Sumberwringin","Tamankrocok","Tamanan","Tapen","Tegalampel","Tenggarang","Tlogosari","Wonosari","Wringin")
        ),
        alamatmodel(
            "Gresik",
            arrayOf("Balongpanggang", "Benjeng","Bungah","Cerme","Driyorejo","Duduk Sampeyan","Dukun","Gresik","Kebomas","Kedamean","Manyar","Menganti","Panceng","Sangkapura","Sidayu",
                "Tambak","Ujung Pangkah","Wringinanom")
        ),
        alamatmodel(
            "Kabupaten Jember",
            arrayOf("Ajung","Ambulu","Arjasa","Balung","Bangsalsari","Gumukmas","Jelbuk","Jenggawah","Jombang","Kalisat","Kaliwates","Kencong","Ledokombo","Mayang",
                "Mumbulsari","Pakusari","Panti","Patrang","Puger","Rambipuji","Semboro","Silo","Sukorambi","Sukowono","Sumberbaru","Sumberjambe",
                "Sumbersari","Tanggul","Tempurejo","Umbulsari","Wuluhan")
        ),
        alamatmodel(
            "Kabupaten Jombang",
            arrayOf("Bandar Kedungmulyo","Bareng","Diwek","Gudo","Jogoroto","Jombang","Kabuh","Kesamben","Kudu","Megaluh","Mojoagung","Mojowarna","Ngoro","Ngusikan",
                "Perak","Peterongan","Plandaan","Ploso","Sumobito","Tembelang","Wonosalam")
        ),
        alamatmodel(
            "Kabupaten Kediri",
            arrayOf("Badas","Banyakan","Gampengrejo","Grogol","Gurah","Kandangan","Kandat","Kayen Kidul","Kepung","Kras","Kunjang","Mojo","Ngadiluwih","Ngancar",
                "Ngasem","Pagu","Papar","Pare","Plemahan","Plosoklaten","Puncu","Purwoasri","Ringinrejo","Semen","Tarokan","Wates")
        ),
        alamatmodel(
            "Kabupaten Lamongan",
            arrayOf("Babat","Blubuk","Brondong","Deket","Glagah","Kalitengah","Karangbinangun","Karanggeneng","Kedungpring","Kembangbahu","Lamongan","Lamongan",
                "Laren","Maduran","Mantup","Modo","Ngimbang","Paciran","Pucuk","Sambeng","Sarirejo","Sekaran","Solokuro","Sugio","Sukodadi","Sukorame","Tikung","Turi")
        ),
        alamatmodel(
            "Kabupaten Lumajang",
            arrayOf("Candirpuro","Gucialit","Jatirito","Kedungjajang","Klakah","Kunir","Lumajang","Padang","Pasirian","Pasrujambe","Pronojiwo","Randuagung","Ranuyoso",
                "Rowokagung","Sukodono","Sumbersuko","Senduro","Tekung","Tempeh","Tempursari","Yosowilangun")
        ),
        alamatmodel(
            "Kabupaten Magetan",
            arrayOf("Barat","Bendo","Karangrejo","Karas","Kertoharjo","Kawedanan","Lambeyan","Lambeyan","Magetan","Maospati","Ngariboyo","Nguntoronadi","Panekan","Parang",
                "Parang","Plaosan","Poncol","Sidorejo","Sukomoro","Takeran")
        ),
        alamatmodel(
            "Kabupaten Malang",
            arrayOf("AmpelGading","Bantur","Bululawang","Dampit","Dau","Donomulyo","Gedangan","Gondanglegi","Jabung","Kalipare","Karangploso","Kasembon","Kepanjen",
                "Kromengan","Lawang","Ngajum","Ngantang","Pagak","Pagelaran","Pakis","Pakisaji","Poncokusumo","Singosari","Sumberpucung","Tajinan","Tirtoyudo",
                "Tumpang","Turen","Wagir","Wajak","Wonosori")
        ),
        alamatmodel(
            "Kabupaten Mojokerto",
            arrayOf("Dawarblandong","Kemlangi","Jetis","Gedeg","Mojoanyar","Sooko","Bangsal","Puri","Trowulan","Jatirejo","Dlanggu","Mojosari","Pungging","Kutorejo",
                "Ngoro","Gondang","Trawas","Pacet")
        ),
        alamatmodel(
            "Kabupaten Nganjuk",
            arrayOf("Bagor","Baron","Berbek","Gondang","Jatikalen","Kertosono","Lengkong","Loceret","Nganjuk","Ngetos","Ngluyu","Ngronggot","Pace","Patianrowo","Prambon","Rejoso",
                "Sawahan","Sukomoro","Tanjunganom","Wilangan")
        ),
        alamatmodel(
            "Kabupaten Ngawi",
            arrayOf("Bringin","Geneng","Jogorogo","Karangjati","Kedunggalar","Kendal","Kwadungan","Mantingan","Ngawi","Ngrambe","Padas","Pangkur","Paron","Pitu","Sine","Widodaren",
                "Karanganyar","Kasreman","Gerih")
        ),
        alamatmodel(
            "Kabupaten Pacitan",
            arrayOf("Pacitan","Kebonagung","Arjosari","Tulakan","Ngadirojo","Punung","Pringkuku","Donorojo","Nawangan","Tegalombo","Sudimoro","Bandar")
        ),
        alamatmodel(
            "Kabupaten Pamekasan",
            arrayOf("Waru","Pakong","Batu Marmar","Galis","Kadur","Larangan","Pademawu","Palengaan","Pamekasan","Pasean","Pegantenan","Proppo","Tlanakan")
        ),
        alamatmodel(
            "Kabupaten Pasuruan",
            arrayOf("Bangil","Beji","Gempol","Gondang Wetan","Grati","Kejayan","Kraton","Lekok","Lumbang","Nguling","Pandaan","Pasrepan","Pohjentrek",
                "Prigen","Purwodadi","Purwosari","Puspo","Rejoso","Rembang","Sukorejo","Tosari","Tutur","Winongan","Wonorejo")
        ),
        alamatmodel(
            "Kabupaten Probolinggo",
            arrayOf("Bantaran","Banyuanyar","Besuki","Dringu","Gading","Gending","Kotaanyar","Kraksaan","Krejengan","Krucil","Kuripan","Leces","Lumbang",
                "Maron","Paiton","Pajarakan","Pakuniran","Sukapura","Sumber","Sumberasih","Tegalsiwalan","Tiris","Tongas","Wonomerto")
        ),
        alamatmodel(
            "Kabupaten Sampang",
            arrayOf("Banyuates","Camplong","Jrengik","Kedungdung","Ketapang","Omben","Robatal","Sampang","Sokobanah","Sreseh","Tambelangan","Torjun")
        ),
        alamatmodel(
            "Kabupaten Sidoarjo",
            arrayOf("Sidoarjo","Balongbendo","Buduran","Candi","Gedangan","Jabon","Krembung","Krian","Prambon","Porong","Sedati","Sukodono","Taman",
                "Tanggulangin","Tarik","Tulangan","Waru","Wonoayu")
        ),
        alamatmodel(
            "Kabupaten Situbondo",
            arrayOf("Banyuglugur","Jatibanteng","Sumber Malang","Besuki","Suboh","Mlandingan","Bungatan","Kendit","Panarukan","Situbondo","Panji",
                "Mangaran","Kepongan","Arjasa","Jangkar","Asembagus","Banyuputih")
        ),
        alamatmodel(
            "Kabupaten Sumenep",
            arrayOf("Ambunten","Arjasa","Batang Batang","Batuan","Batuputih","Bluto","Dasuk","Dungkek","Ganding","Gapura","Gayam","Giligenteng","Gulukguluk",
                "Kalianget","Kangayan","Kota Sumenep","Lenteng","Manding","Masalembo","Nonggunong","Pasongsongan","Pragaan","Raas","Rubaru",
                "Sapeken","Saronggi","Talango")
        ),
        alamatmodel(
            "Kabupaten Trenggalek",
            arrayOf("Bendungan","Dongko","Durenan","Gandusari","Kampak","Karangan","Munjungan","Panggul","Pogalan","Pule","Suruh","Kota Trenggalek","Tugu","Watulimo")
        ),
        alamatmodel(
            "Kabupaten Tuban",
            arrayOf("Bancar","Bangilan","Grabagan","Jatirogo","Jenu","Kenduruan","Kerek","Merakurak","Montong","Palang","Parengan","Plumpang","Rengel",
                "Semanding","Senori","Singgahan","Soko","Tambakboyo","Widang","Grabagan")
        ),
        alamatmodel(
            "Kabupaten Tulungagung",
            arrayOf("Bandung","Besuki","Boyolangu","Campurdarat","Gondang","Kalidawir","Karangrejo","Kauman","Kedungwaru","Ngantru","Ngunut","Pagerwojo","Pakel","Pucanglaban",
                "Rejotangan","Sendang","Sumbergempol","Tanggung Gunung","Tulungagung")
        ),
        alamatmodel(
            "Kota Batu",
            arrayOf("Batu","Bumiaji","Junrejo")
        ),
        alamatmodel(
            "Kota Blitar",
            arrayOf("Kepanjenkidul","Sananwetan","Sukorejo")
        ),
        alamatmodel(
            "Kota Kediri",
            arrayOf("Kepanjenkidul","Sananwetan","Sukorejo")
        ),
        alamatmodel(
            "Kota Malang",
            arrayOf("Kendungkandang","Sukun","Klojen","Blimbing","Lowokwaru")
        ),
        alamatmodel(
            "Kota Mojokerto",
            arrayOf("Magersari","Prajurit Kulon","Kranggan")
        ),
        alamatmodel(
            "Kota Pasuruan",
            arrayOf("Bugulkidul","Gadingrejo","Purworejo","Panggungrejo")
        ),
        alamatmodel(
            "Kota Probolinggo",
            arrayOf("Kademangan","Mayangan","Kanigaran","Wonoasih","Kedopok")
        )
        )
}