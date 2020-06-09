package models

case class Witness(id: Int, firstName: Option[String], lastName: Option[String], phone: Option[String]) {

  def partialCopy(w: Witness) = {
             this.copy(
               firstName = if(w.firstName.isEmpty) this.firstName else w.firstName,
               lastName = if(w.lastName.isEmpty) this.lastName else w.lastName,
               phone = if(w.phone.isEmpty) this.phone else w.phone
          )
  }

}
