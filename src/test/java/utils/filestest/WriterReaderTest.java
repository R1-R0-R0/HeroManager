package utils.filestest;

import model.items.consumables.Consumable;
import org.junit.Test;
import utils.files.FileReaders;
import utils.files.Writer;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class WriterReaderTest {

    @Test
    public void testWriterReaderConsumable() throws IOException {
        List<Consumable> consumables = new ArrayList<>();
        consumables.add(new Consumable("healing pot","ça soigne"));

        Writer.writerConsumable(consumables);
        List<Consumable> test = new ArrayList<>();
        test.addAll(FileReaders.getConsumable());

        assertTrue (consumables.get(0).getName().equals(test.get(0).getName()));
        assertTrue(consumables.get(0).getDescription().equals(test.get(0).getDescription()));
    }
}
