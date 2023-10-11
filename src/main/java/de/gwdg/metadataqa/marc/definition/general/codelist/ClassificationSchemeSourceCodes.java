package de.gwdg.metadataqa.marc.definition.general.codelist;

import de.gwdg.metadataqa.marc.Utils;

/**
 * Classification Scheme Source Codes
 * http://www.loc.gov/standards/sourcelist/classification.html
 * used in Bibliographic records
 * 052 $2 (Geographic Classification / Source)
 * 055 $2 (Classification Numbers Assigned in Canada / Source of call/class number)
 * 084 $2 (Other Classification Number / Source of number)
 * 086 $2 (Government Document Classification Number / Number source)
 * 852 $2 (Location / Source of classification or shelving scheme)
 */
public class ClassificationSchemeSourceCodes extends CodeList {

  private void initialize() {
    name = "Classification Scheme Source Codes";
    url = "http://www.loc.gov/standards/sourcelist/classification.html";
    codes = Utils.generateCodes(
      "accs", "Annehurst curriculum classification system (West Lafayette, IN: Kappa Delta Pi)",
      "acmccs", "ACM Computing Classification System [2008 Version]",
      "agricola", "AGRICOLA subject category codes (Agriculture Network Information Center)",
      "agrissc", "AGRIS: subject categories (Rome: AGRIS Coordinating Centre)",
      "anscr", "The Alpha-numeric system for classification of recordings (Williamsport, PA: Bro-Dart)",
      "ardocs", "Arkansas state documents classification scheme (Little Rock: Arkansas State Library, Documents Services Section, State Publications Unit)",
      "asb", "Allgemeine Systematik für öffentliche Bibliotheken (ASB) (Berlin: Deutsches Bibliotheksinstitut)",
      "azdocs", "Arizona documents: KWOC manual (Phoenix: State Documents Center, Arizona Dept. of Library, Archives and Public Records)",
      "bar", "Barnard, Cyril C. A classification for medical and veterinary libraries (London: H.K. Lewis)",
      "bcl", "Nederlandse Basisclassificatie = Dutch Basic Classification Codes (The Hague: Koninklijke Bibliotheek)",
      "bcmc", "Coates, E.J. The British catalogue of music classification (London: Council of the British National Bibliography)",
      "bisacsh", "BISAC Subject Headings",
      "bkl", "Basisklassifikation",
      "bliss", "BLISS bibliographic classification (London: Butterworths)",
      "blissc", "British Library Inside service subject classification (London: British Library)",
      "blsrissc", "British Library - Science reference information service subject classification (London: British Library)",
      "cacodoc", "CODOC [Canadian federal and provincial government documents classification scheme] (Toronto: Office of Library Coordination, Council of Ontario Universities)",
      "cadocs", "California state agency classification scheme (Sacramento: Government Publications Section, California State Library)",
      "ccpgq", "Cadre de classement des publications gouvernementales du Québec (Montréal: Bibliothèque et Archives nationales du Québec)",
      "cddir", "Classifiação Decimal de Direito (CDDIR)",
      "celex", "CELEX document number (Luxembourg: Office for Official Publications of the European Communities)",
      "chfbn", "Classification de l'histoire de France (Paris: Bibliothèque nationale de France)",
      "clc", "Zhongguo tu shu guan fen lei fa = Chinese library classification (Beijing: Beijing tu shu guan chu ban she)",
      "clutscny", "Classification of the Library of Union Theological Seminary in the City of New York (New York: Union Theological Seminary)",
      "codocs", "Colorado State Publications Depository and Distribution Center. Classification schedule (Denver: Colorado State Library)",
      "cslj", "Elazar, David H. & Elazar, Daniel J. A classification system for libraries of Judaica (Lanham, MD: University Press of America)",
      "cstud", "Classificatieschema's Bibliotheek TU Delft (Delft: Technische Universiteit Delft, Bibliotheek)",
      "cutterec", "Cutter, Charles Ammi. Expansive classification (Boston: C.A. Cutter)",
      "ddc", "Dewey decimal classification and relative index (Dublin, Ohio: OCLC Online Computer Center)",
      "dopaed", "DOPAED der UB Erlangen",
      "egedeklass", "Egede-instituttets klassifikasjonsystem",
      "ekl", "Eduskunnan kirjaston luokitus = Library of Parliament Classification",
      "farl", "Frick Art Reference Library book classification system (New York, NY: Frick Art Reference Library)",
      "farma", "Oversigt over systematisk catalog (København: Danmarks farmaceutiski bibliotek",
      "fcps", "Class FC: a classification for Canadian history, Class PS8000: a classification for Canadian literature (Ottawa: Library and Archives Canada)",
      "fiaf", "Moulds, Michael. Classification scheme for literature on film and television (London: International Federation of Film Archives)",
      "fid", "Fachinformationsdienste für die Wissenschaft (FID)",
      "finagri", "Finagri-luokitus (Helsinki: Maatalouskirjasto - Agricultural Library)",
      "flarch", "Florida State Archives arrangement and description procedures manual (Tallahassee, FL: Florida State Archives)",
      "fldocs", "A Keyword-in-context to Florida public documents in the Florida Atlantic University Library (Tallahassee: Department of State, State Library)",
      "frtav", "Referentiel du format INTERMAC Bibliographique: Audiovisuel - Matière generale (Paris: Bibliothèque nationale de France)",
      "gadocs", "Official publications of the State of Georgia: list of classes with index by name of agency and subject/keyword (Athens, GA: University of Georgia Libraries, Government Documents Department)",
      "gfdc", "Global forest decimal classification (GFDC) (Vienna: IUFRO)",
      "ghbs", "GHB-Aufstellungssystematik: HBZ Köln",
      "iadocs", "Classification of Iowa state documents (Des Moines: State Library of Iowa)",
      "ics", "International Classification for Standards (International Organization for Standardization)",
      "ifzs", "Systematik der IfZ-Bibliothek",
      "inspec", "INSPEC classification (Edison, NJ: INSPEC Inc.)",
      "ipc", "International Patent Classification",
      "ivdcc", "intranda viewer digital collection classification (Göttingen: intranda GmbH)",
      "jelc", "Journal of Economic Literature (JEL) classification",
      "jstormcs", "JSTOR Multidisciplinary Classification Scheme",
      "kab", "Klassifikation für Allgemeinbibliotheken (Bad Honnef: Bock und Herchen Verlag)",
      "kfmod", "KF classification, modified for use in Canadian law libraries (Downsview, Ont.: York University Law Library)",
      "kktb", "Kokuritsu kokkai toshokan bunruihyo = National Diet Library classification (Tokyo: National Diet Library)",
      "knt", "Klassifikasjonsnøkkel til norsk topografi (Trondheim: Universitetsbiblioteke)",
      "ksdocs", "State documents of Kansas: list of classes (Topeka: State Library of Kansas)",
      "kssb", "Klassifikationssystem for svenska bibliotek (Lund: Bibliotekstjanst)",
      "kuvacs", "Kuvataideakatemian kirjaston luokitusjärjestelmä = Finnish Academy of Fine Arts Library Classification (Helsinki: Kuvataideakatemian kirjasto)",
      "laclaw", "Los Angeles County Law Library, class K-Law (Los Angeles: County Law Library)",
      "ladocs", "Louisiana documents classification schedule (Baton Rouge: Louisiana State Library)",
      "lcc", "Library of Congress classification (Washington , D.C.: Library of Congress, CDS)",
      "loovs", "Løøvs klassifikationssystem (Stockholm: Kungliga biblioteket)",
      "methepp", "Methode Eppelsheimer",
      "mf-klass", "Klassifikasjonssystemet ved Menighetsfakultetes bibliotek",
      "midocs", "Michigan documents classification scheme (Lansing: Michigan Dept. of Education, State Library Services)",
      "misklass", "Klassifikasjonsskjema (Stavenger: Misjonshogskolen)",
      "mmlcc", "Manual of map library classification and cataloguing (London: Ministry of Defence)",
      "modocs", "Missouri state documents classification: post-reorganization agency codes and form divisions (Jefferson City: Missouri State Library)",
      "moys", "Moys, Elizabeth M. Moys classification and thesaurus for legal materials (London: Bowker-Saur)",
      "mpilcs", "MPI Luxembourg Classification System (Max Planck Institute Luxembourg)",
      "mpkkl", "Maanpuolustuskorkeakoulun kirjaston luokitusjärjestelmä",
      "msc", "Mathematical subject classification (Providence, RI: American Mathematical Society)",
      "msdocs", "Mississippi state government publications. Vol. 1, July 1975/June 1980 (Jackson: Mississippi Library Commission)",
      "mu", "Clasificación Musical de la Bibliografía Nacional de España (Madrid: Biblioteca Nacional de España)",
      "naics", "North American industry classification system (Washington DC: OMB, GPO)",
      "nasasscg", "NASA scope and subject category guide (Hanover, MD: NASA, Scientific and Technical Information Program)",
      "nbdocs", "Guide to Nebraska state agencies: state publications classification and ordering directory (Lincoln: Nebraska Publications Clearinghouse, Nebraska Library Commission)",
      "ncdocs", "Classification scheme for North Carolina state publications: as applied to the documents collection of the N.C. Dept. of Cultural Resources (Raleigh: The State Library)",
      "ncsclt", "New classification scheme for Chinese libraries (Taipei: National Central Library)",
      "nhcp", "NH classification for photography (Ottowa, ON: Art Libraries Society of North America)",
      "nicem", "NICEM subject headings and classification system (Albuquerque, NM: National Information Center for Educational Media)",
      "niv", "Norsk inndeling av vitenskapsdisipliner (Norway: Universitets- og høgskolerådet)",
      "njb", "Nihon jisshin bunruihō = Nippon decimal classification (Tōkyō: Nihon Toshokan Kyōkai)",
      "nlm", "National Library of Medicine classification (Bethesda, MD: NLM)",
      "nmdocs", "The New Mexico state documents classification system (Santa Fe: New Mexico State Library)",
      "no-ujur-cmr", "Menneskerettighets-klassifikasjon (Norwegian Center for Human Rights, University of Oslo)",
      "no-ujur-cnip", "NifsP-klassifikasjon",
      "no-ureal-ca", "Astrofysikk",
      "no-ureal-cb", "Biologisk hylleoppstilling",
      "no-ureal-cg", "Geofysikk oppstillingssystem",
      "noterlyd", "Klassifikasjonssystem for noter og lydopptak = Music classification system (Sheet music and recordings) (Norges musikkhgskole Biblioteket)",
      "nvdocs", "Nevada state documents (Carson City: Nevada State Library and Archives)",
      "nwbib", "Nordrhein-Westfälische Bibliographie (Köln: hbz - Hochschulbibliothekszentrum NRW)",
      "nydocs", "New York state documents: an introductory manual (Albany: New York State Library)",
      "ohdocs", "Ohio documents classification scheme (Columbus: State Library of Ohio)",
      "okdocs", "Oklahoma state documents classification and list of Oklahoma state agencies from statehood to the present (Oklahoma City: Oklahoma Dept. of Libraries)",
      "oosk", "Oversikt over systematisk katalog (Norway: Universitetsbiblioteket i Bergen)",
      "ordocs", "OrDocs: history authority list and classification scheme for Oregon state agencies (Salem: Oregon State Library)",
      "padocs", "Classification scheme for Pennsylvania state publications (Harrisburg: State Library of Pennsylvania)",
      "pim", "PIM [Presentatiesysteem Informatieve Media] (Zoetermeer: NBD Biblion)",
      "pssppbkj", "Popis strucnih skupina i svih podskupina s podacima o broju kataloskih jedinica (Zagreb: Nacionalna i Sveucilisna Biblioteka)",
      "rich", "Richardson classification system (Yardley, Pa.: F S. Cook & Son)",
      "ridocs", "Alphabetical list of state agencies and corresponding Swank classification (Providence: Rhode Island State Library)",
      "rilm", "RILM classification system",
      "rpb", "Rheinland-Pfälzische Bibliographie",
      "rswk", "Regeln für den Schlagwortkatalog (Berlin: Deutsches Bibliotheksinstitut)",
      "rubbk", "Tablitsy bibliotechno-bibliograficheskoi klassifikatsii dlíà nauchnykh bibliotek v 30-ti tomakh (Moskva: Kniga)",
      "rubbkd", "Tablitsy bibliotechno-bibliograficheskoi klassifikatsii dlíà detskikh bibliotek v 1 t. (Moskva: Kniga)",
      "rubbkk", "Tablitsy bibliotechno-bibliograficheskoi klassifikatsii dlíà kraevedcheskikh katalogov bibliotek (Moskva: Kniga)",
      "rubbkm", "Tablitsy bibliotechno-bibliograficheskoi klassifikatsii dlíà massovykh bibliotek v 1. t. (Moskva: Kniga)",
      "rubbkmv", "Tablitsy bibliotechno-bibliograficheskoi klassifikatsii dlíà massovykh voennykh bibliotek (Moskva: Kniga)",
      "rubbkn", "Tablitsy bibliotechno-bibliograficheskoi klassifikatsii dlíà nauchnykh bibliotek v 4-kh tomakh (Moskva: Kniga)",
      "rubbknp", "Pereizdanidiíà tablits bibliotechno-bibliograficheskoi klassifikatsii dlíà nauchnykh bibliotek v 30-ti tomakh (Moskva: Kniga)",
      "rubbko", "Tablitsy bibliotechno-bibliograficheskoi klassifikatsii dlíà oblastvykh bibliotek v 4-kh tomakh (Moskva: Kniga)",
      "rubbks", "Bibliotechno-bibliograficheskaíà klassifikatsiíàe: Srednye tablitsy (Moskva: Izdatel'stvo Liberiíà)",
      "rueskl", "Edinaíà skhema klassifikatsii literatury dlíà knigoizdaniíà v SSSR (Moskva: Kniga)",
      "rugasnti", "Rubrikator Gosudarstvennoi avtomatizirovannoi sistemy nauchno-tekhnicheskoi informatsii (Moskva: Vsesoíàznyi institut nauchnoi i tekhnicheskoi informatsii)",
      "rvk", "Regensburger Verbundklassifikation (RVK)",
      "sbb", "Systematik der Bayerischen Bibliographie",
      "scdocs", "South Carolina state documents classification system (Columbia: State Library)",
      "sddocs", "[South Dakota] State documents classification schedule (Pierre: South Dakota State Library)",
      "sdnb", "Systematik der Deutschen Nationalbibliographie",
      "sfb", "SfB: Systematik für Bibliotheken (München: K.G. Saur)",
      "siblcs", "Sibelius-Akatemian kirjaston luokitusjärjestelmä = Sibelius Academy Library classification system",
      "siso", "Schema voor de Indeling van de Systematische Catalogus in Openbare Bibliotheken (Zoetermeer: NBD Biblion)",
      "skb", "Sachbuch-Systematik für Katholische öffentliche Büchereien (München: St. Michaelsbund)",
      "smm", "Systematik des Musikschrifttums u.d. Musikalien fr ffentl. Musikbibliotheken SMM (Bergen: Universitetetsbiblioteket i Bergen",
      "ssd", "Systematik: Stadtbücherei Duisburg: Buchaufstellung und Ordnung des systematischen Kataloges (Reutlingen: Verlag Buch und Bibliothek)",
      "ssgn", "Sondersammelgebiets-Nummer",
      "sswd", "Subject Classes of the Schlagwortnormdatei",
      "stub", "Systematik der TUB München",
      "suaslc", "Seinäjoen korkeakoulukirjaston luokitus (Seinäjoki: Seinäjoen korkeakoulukirjaston luokitus)",
      "sudocs", "Superintendent of Documents classification system (Washington, D.C.: Government Printing Office)",
      "swank", "Swank, Raynard Coe. A classification for state, county, and municipal documents (Boulder, CO: University of Colorado Library)",
      "taikclas", "Taideteollisen korkeakoulun kirjaston luokitus = University of Art and Design Helsinki Library Classification",
      "taykl", "Tampereen yliopiston kirjaston luokitus: Systemaattinen osa & Aakkosellinen osa (Tampere: Tampereen yliopisto)",
      "teatkl", "Teatterikorkeakoulun kirjaston luokitusopas",
      "txdocs", "Texas state documents classification & almost compleat [sic] list of Texas state agencies from statehood to the present (Austin: Legislative Reference Library & Government Publications Library, Texas State Library)",
      "tykoma", "Turun yliopiston kirjaston vanha luokitus",
      "ubtkl/2", "Klassifikasjonsskjema (Trondheim: Fellesbiblioteket, Kongelige Norske videnskabers selskab, Museet, Norges lærerhøgskole)",
      "udc", "Universal decimal classification (London: British Standards Institute)",
      "uef", "Itä-Suomen yliopiston kokoelmaluokitus = Collection classification of The University of Eastern Finland (Joensuu, Kuopi & Savonlinnassa: Itä-Suomen yliopisto)",
      "undocs", "United Nations document series symbols: 1946-77 cumulative (New York: UN)",
      "upsylon", "UPSYLON: classification systématique de la Bibliothèque de la Faculté de psychologie et des sciences de l'éducation de l'Université catholique de Louvain (Louvain-la-Neuve, Belgique: La Faculté)",
      "usgslcs", "U.S. Geological Survey library classification (Reston, VA: U.S. Geological Survey Library)",
      "utdocs", "Utah documents classification schedules (Salt Lake City: Utah State Library Division)",
      "utk", "University of Oslo Library Classification (Oslo: Universitetet i Oslo)",
      "utklklass", "L-klassifikasjon (Oslo: Universitetet i Oslo)",
      "utklklassex", "L-klassifikasjon with extensions (Oslo: Universitetet i Oslo)",
      "veera", "VEERA-luokitus = VEERA-классификация",
      "vsiso", "Vlaamse SISO [schema voor de indeling van de systematische catalogus in openbare bibliotheken] (Antwerpen: VLABIN-VBC)",
      "wadocs", "Washington State Library state documents collection: State documents call number (Olympia: State Library)",
      "widocs", "Organizing Wisconsin public documents: cataloging and classification of documents at the State Historical Society Library (Madison: Division for Library Services, Bureau for Reference and Loan Services)",
      "wydocs", "WyDocs: the Wyoming state documents classification system (Cheyenne, WY: Department of Administration and Information, Wyoming State Library)",
      "ykl", "Yleisten kirjastojen luokitusjärjestelmä = Finnish public libraries classification system",
      "z", "Other [used when a source code has not yet been assigned to a classification scheme]",
      "zdbs", "ZDB-Systematik = ZDB-Classification",

      // PICA
      // "sdnb", "Sachgruppen der Deutschen Nationalbibliografie bis 2003"
      "nb", "Notation – Beziehung"

    );
    indexCodes();
  }

  private static ClassificationSchemeSourceCodes uniqueInstance;

  private ClassificationSchemeSourceCodes() {
    initialize();
  }

  public static ClassificationSchemeSourceCodes getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new ClassificationSchemeSourceCodes();
    return uniqueInstance;
  }
}