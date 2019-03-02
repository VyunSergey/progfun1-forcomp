package object forcomp {
  val dictionaryPath = List("forcomp", "linuxwords.txt")

  def loadDictionary: List[String] = {
    val word_stream = Option {
      getClass.getResourceAsStream(dictionaryPath.mkString("/"))
    } orElse {
      common.resourceAsStreamFromSrc(dictionaryPath)
    } getOrElse {
      sys.error("Could not load word list, dictionary file not found")
    }
    try {
      val s = io.Source.fromInputStream(word_stream)
      s.getLines.toList
    } catch {
      case e: Exception =>
        println("Could not load word list: " + e)
        throw e
    } finally {
      word_stream.close()
    }
  }

}
