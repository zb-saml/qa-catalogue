from lxml import etree
import io
from sickle import Sickle

namespaces = {
    'oai': 'http://www.openarchives.org/OAI/2.0/',
    'marc21': 'http://www.loc.gov/MARC21/slim'
} 

sickle = Sickle('https://libris.kb.se/api/oaipmh/', max_retries=4)

header = '<?xml version="1.0" encoding="utf8"?>' + "\n" + '<collection>' + "\n" 
footer = '</collection>'

output = []
i = 0
it = sickle.ListRecords(metadataPrefix='marcxml_expanded', set='bib:S')
for record in it:
    tree = etree.ElementTree(record.xml)
    token = tree.xpath('/resumptionToken[0]', namespaces=namespaces)
    print(etree.tostring(token, encoding='utf8', method='xml').decode("utf-8"))

    recs = tree.xpath('/oai:record[*]/oai:metadata/marc21:record', namespaces=namespaces)
    for rec in recs:
        core = etree.tostring(rec, encoding='utf8', method='xml').decode("utf-8")
        output.append(core.replace("<?xml version='1.0' encoding='utf8'?>\n", ''))
    if len(output) >= 1000:
        file_name = "%06d.xml" % (i)
        print(file_name)
        with io.open(file_name, 'w', encoding='utf8') as f:
            f.write(header)
            f.write("\n".join(output) + "\n")
            f.write(footer)
        output = []
        i = i + 1
