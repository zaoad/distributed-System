import java.util.*;

public class jokes {
    public Map<String, String> jokesMap;
    public List<String> namesList;
    public List<String> jokeskeys ;
    public jokes()
    {
        namesList= Arrays.asList( "Cow says", "A little old lady", "Canoe","Annie","Nanna","Amos","Dozen","Water","Thermos","Needle",
            "Stupid","Some","Snow","Says","Iran","Howl","Alec","Olive","Razor","Dwayne","Mikey",
            "Lettuce","honey bee","Dewey","To","Stopwatch","Boo","Candice","Spell","Voodoo");
        jokesMap = new HashMap<>();
        jokeskeys = new ArrayList<>();
        jokeskeys.addAll(namesList);

        jokesMap.put("COW SAYS", "No, a cow says mooooo!");
        jokesMap.put("A LITTLE OLD LADY", "All this time, I had no idea you could yodel.");
        jokesMap.put("CANOE","Canoe come out or what?");
        jokesMap.put("ANNIE","Annie way you can let me in soon?");
        jokesMap.put("NANNA","Nanna your business, that's who.");
        jokesMap.put("AMOS","A mosquito");
        jokesMap.put("DOZEN","Dozen anyone wanna let me in?");
        jokesMap.put("WATER","Water you doing? Just open the door!");
        jokesMap.put("THERMOS","Thermos be a better way to get throuhg to you");
        jokesMap.put("NEEDLE","Needle little help gettin’ through.");
        jokesMap.put("STUPID","Stupid you, that’s who.");
        jokesMap.put("SOME","Some day you’ll recognize me, hopefully.");
        jokesMap.put("SNOW","Snow use askin’ when you can just open.");
        jokesMap.put("SAYS","Says me, that’s who.");
        jokesMap.put("IRAN","Iran all the way here. Let me in already!");
        jokesMap.put("HOWL","Howl you know unless you open the door?");
        jokesMap.put("ALEC","Alec-tricity. Ain’t that a shocker?");
        jokesMap.put("OLIVE","Olive you. Do you love me too?");
        jokesMap.put("RAZOR","Razor hands, this is a stick up!");
        jokesMap.put("DWAYNE","Dwayne the bathtub already. I’m drowning!");
        jokesMap.put("MIKEY","Mikey doesn’t work so help me out, would you?");
        jokesMap.put("LETTUCE","Lettuce in, it’s cold out here!");
        jokesMap.put("HONEY BEE","Honey bee a dear and open up, would you?");
        jokesMap.put("DEWEY","Dewey have to use a condom every time?");
        jokesMap.put("TO","It’s to whom.");
        jokesMap.put("STOPWATCH","Stopwatch you’re doing and pay attention!");
        jokesMap.put("BOO","No need to cry, it’s only a joke.");
        jokesMap.put("CANDICE","Candice door open, or what?");
        jokesMap.put("SPELL","Okay, okay: W. H. O.");
        jokesMap.put("VOODOO","Voodoo you think you are, asking me so many questions?");
    }

}
