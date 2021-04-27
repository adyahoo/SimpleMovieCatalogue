package id.ac.mymoviecatalogue.utils

import id.ac.mymoviecatalogue.R
import id.ac.mymoviecatalogue.data.MoviesEntity
import id.ac.mymoviecatalogue.data.TvShowEntity

object DataDummy {
    fun getMovies(): ArrayList<MoviesEntity> {
        val movies = ArrayList<MoviesEntity>()

        movies.add(
            MoviesEntity(
            "m01",
            "Alita: Battle Angel (2019)",
            "02/14/2019",
            "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "Action, Science Fiction, Adventure",
                "Robert Rodriguez",
                R.drawable.poster_alita
            )
        )
        movies.add(
            MoviesEntity(
                "m02",
                "Bohemian Rhapsody (2018)",
                "11/02/2018",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "Music, Drama, History",
                "Bryan Singer",
                R.drawable.poster_bohemian
            )
        )
        movies.add(
            MoviesEntity(
                "m03",
                "Creed II (2018)",
                "11/21/2018",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "Drama",
                "Steven Caple Jr.",
                R.drawable.poster_creed
            )
        )
        movies.add(
            MoviesEntity(
                "m04",
                "Fantastic Beast: The Crimes of Grindelwald (2018)",
                "11/16/2018",
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "Adventure, Fantasy, Drama",
                "David Yates",
                R.drawable.poster_crimes
            )
        )
        movies.add(
            MoviesEntity(
                "m05",
                "How to Train Your Dragon: The Hidden World (2019)",
                "02/22/2019",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "Animation, Family, Adventure",
                "Dean DeBlois",
                R.drawable.poster_how_to_train
            )
        )
        movies.add(
            MoviesEntity(
                "m06",
                "Avengers: Infinity War (2018)",
                "04/27/2018",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "Adventure, Action, Science Fiction",
                "Anthony Russo, Joe Russo",
                R.drawable.poster_infinity_war
            )
        )
        movies.add(
            MoviesEntity(
                "m07",
                "Master Z: Ip Man Legacy (2018)",
                "12/26/2018",
                "Following his defeat by Master Ip, Cheung Tin Chi tries to make a life with his young son in Hong Kong, waiting tables at a bar that caters to expats. But it's not long before the mix of foreigners, money, and triad leaders draw him once again to the fight.",
                "Action",
                "Yuen Woo-Ping",
                R.drawable.poster_master_z
            )
        )
        movies.add(
            MoviesEntity(
                "m08",
                "Mortal Engines (2018)",
                "12/14/2018",
                "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
                "Adventure, Science Fiction",
                "Christian Rivers",
                R.drawable.poster_mortal_engines
            )
        )
        movies.add(
            MoviesEntity(
                "m09",
                "Overlord (2018)",
                "11/09/2018",
                "France, June 1944. On the eve of D-Day, some American paratroopers fall behind enemy lines after their aircraft crashes while on a mission to destroy a radio tower in a small village near the beaches of Normandy. After reaching their target, the surviving paratroopers realise that, in addition to fighting the Nazi troops that patrol the village, they also must fight against something else.",
                "Horror, War, Science Fiction",
                "Julius Avery",
                R.drawable.poster_overlord
            )
        )
        movies.add(
            MoviesEntity(
                "m10",
                "Ralph Breaks the Internet (2018)",
                "11/21/2018",
                "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, Sugar Rush. In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
                "Family, Animation, Comedy, Adventure",
                "Phil Johnston, Rich Moore",
                R.drawable.poster_ralph
            )
        )
        movies.add(
            MoviesEntity(
                "m11",
                "Robin Hood (2018)",
                "11/21/2018",
                "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
                "Adventure, Action, Thriller",
                "Otto Bathurst",
                R.drawable.poster_robin_hood
            )
        )
        movies.add(
            MoviesEntity(
                "m12",
                "Spider-Man: Into the Spider-Verse (2018)",
                "12/14/2018",
                "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                "Action, Adventure, Animation, Science Fiction, Comedy",
                "Rodney Rothman, Bob Persichetti, Peter Ramsey",
                R.drawable.poster_spiderman
            )
        )
        return movies
    }

    fun getShows(): ArrayList<TvShowEntity> {
        val shows = ArrayList<TvShowEntity>()

        shows.add(
            TvShowEntity(
                "s01",
                "Arrow (2012)",
                "2012",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "Greg Berlanti, Marc Guggenheim, Andrew Kreisberg",
                "Crime, Drama, Mystery, Action & Adventure",
                R.drawable.poster_arrow
            )
        )
        shows.add(
            TvShowEntity(
                "s02",
                "Dragon Ball (1986)",
                "1986",
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish.",
                "Akira Toriyama",
                "Animation, Action & Adventure, Sci-Fi & Fantasy",
                R.drawable.poster_dragon_ball
            )
        )
        shows.add(
            TvShowEntity(
                "s03",
                "Fairy Tail (2009)",
                "2009",
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
                "Hiro Mashima",
                "Action & Adventure, Animation, Comedy, Sci-Fi & Fantasy",
                R.drawable.poster_fairytail
            )
        )
        shows.add(
            TvShowEntity(
                "s04",
                "Family Guy (1999)",
                "1999",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                "Seth MacFarlane",
                "Animation, Comedy",
                R.drawable.poster_family_guy
            )
        )
        shows.add(
            TvShowEntity(
                "s05",
                "The Flash (2014)",
                "2014",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "Greg Berlanti, Geoff Johns, Andrew Kreisberg",
                "Drama, Sci-Fi & Fantasy",
                R.drawable.poster_flash
            )
        )
        shows.add(
            TvShowEntity(
                "s06",
                "Game of Thrones (2011)",
                "2011",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "David Benioff, D. B. Weiss",
                "Sci-Fi & Fantasy, Drama, Action & Adventure",
                R.drawable.poster_got
            )
        )
        shows.add(
            TvShowEntity(
                "s07",
                "Marvel's Iron Fist (2017)",
                "2017",
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                "Scott Buck",
                "Action & Adventure, Drama, Sci-Fi & Fantasy",
                R.drawable.poster_iron_fist
            )
        )
        shows.add(
            TvShowEntity(
                "s08",
                "Naruto Shippuden (2007)",
                "2007",
                "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
                "Masashi Kishimoto",
                "Animation, Action & Adventure, Sci-Fi & Fantasy",
                R.drawable.poster_naruto_shipudden
            )
        )
        shows.add(
            TvShowEntity(
                "s09",
                "One Piece (1999)",
                "1999",
                "Years ago, the fearsome pirate king Gol D. Roger was executed, leaving a huge pile of treasure and the famous \"One Piece\" behind. Whoever claims the \"One Piece\" will be named the new pirate king. Monkey D. Luffy, a boy who consumed one of the \"Devil's Fruits\", has it in his head that he'll follow in the footsteps of his idol, the pirate Shanks, and find the One Piece. It helps, of course, that his body has the properties of rubber and he's surrounded by a bevy of skilled fighters and thieves to help him along the way. Monkey D. Luffy brings a bunch of his crew followed by, Roronoa Zoro, Nami, Usopp, Sanji, Tony-Tony Chopper, Nico Robin, Franky, and Brook. They will do anything to get the One Piece and become King of the Pirates!..",
                "Eichiro Oda",
                "Action & Adventure, Comedy, Animation",
                R.drawable.poster_one_piece
            )
        )
        shows.add(
            TvShowEntity(
                "s10",
                "Supergirl (2015)",
                "2015",
                "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                "Greg Berlati, Ali Adler, Andrew Kreisberg",
                "Drama, Sci-Fi & Fantasy, Action & Adventure",
                R.drawable.poster_supergirl
            )
        )
        shows.add(
            TvShowEntity(
                "s11",
                "Supernatural (2005)",
                "2005",
                "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way.",
                "Eric Kripke",
                "Drama, Mystery, Sci-Fi 7 Fantasy",
                R.drawable.poster_supernatural
            )
        )
        shows.add(
            TvShowEntity(
                "s12",
                "The Simpsons (1989)",
                "1989",
                "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                "Matt Groening",
                "Family, Animation, Comedy",
                R.drawable.poster_the_simpson
            )
        )
        return shows
    }
}