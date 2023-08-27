import org.junit.*
import Assert.*

class Tests:

  // TODO convert entire project to proper test suite

  @Test def runAllMainMethods: Unit =
    imperative.main.main(Array.empty)
    imperative.mainFac.main(Array.empty)
    imperative.mainWithParser.main(Array.empty)
    records.mainImperative.main(Array.empty)
    records.mainSimple.main(Array.empty)
    records.mainRecursive.main(Array.empty)
    objects.mainImperative.main(Array.empty)
    objects.mainSimple.main(Array.empty)
    objects.mainRecursive.main(Array.empty)
//    objects.mainWithMethods.main(Array.empty)
    