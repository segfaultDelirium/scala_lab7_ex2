val data = List(
  Map("name" -> "Jan", "fname" -> "Kowalski", "age" -> "45"),
  Map("company" -> "ABB", "origin" -> "Sweden"),
  Map("name" -> "Katarzyna", "fname" -> "Nowak", "age" -> "45"),
  Map("company" -> "F4", "origin" -> "Poland"),
  List("Cos", "innego"),
  Map("company" -> "Salina Bochnia", "origin" -> "Poland"),
  Map("company" -> "AGH", "origin" -> "Poland"),
  Map("name" -> "Krzysztof", "fname" -> "Krol", "age" -> "14")
)

def extra(list: List[Iterable[Any]]) = {
  list.map(x => {
    x.toList match {
      case List(("company", company: String), ("origin", origin: String)) =>
        Company(company, origin)
      case List(
            ("name", firstName: String),
            ("fname", lastName: String),
            ("age", ageString: String)
          ) =>
        Person(firstName, lastName, ageString.toInt)
      case List(word0: String, word1: String, _*) => DiWord(word0, word1)
      case List(_*)                               => DiWord("", "")
      // case _ => Company("", "")
    }
  })
}

case class Person(firstName: String, lastName: String, age: Int) {}

case class Company(name: String, originCountry: String)

case class DiWord(word0: String, word1: String)

def onlyPeople(list: List[Person | Company | DiWord]): List[Person] = {
  list
    .map(x =>
      x match {
        case person: Person => Some(person)
        case _              => None
      }
    )
    .flatten
}

def onlyComp(list: List[Person | Company | DiWord]): List[Company] = {
  list
    .map(x =>
      x match {
        case company: Company => Some(company)
        case _                => None
      }
    )
    .flatten
}

@main def hello: Unit = {
  println(data)
  val p = extra(data)
  println(p)
  println(onlyPeople(p))
  println(onlyComp(p))
}
