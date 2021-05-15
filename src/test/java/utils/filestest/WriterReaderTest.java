package utils.filestest;

import model.items.consumables.Consumable;
import org.junit.Test;
import utils.files.FileReaders;
import utils.files.HeroManagerDB;
import utils.files.Writer;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class WriterReaderTest {

    @Test
    public void testWriterReaderConsumable() throws IOException {
        HeroManagerDB.init();
    }
}
