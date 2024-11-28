<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8"></xsl:output>
    <xsl:template match="/">
        <html>
            <head>
                <title>Y4O4X0</title>
            </head>
            <body>
                <h1>Hallgatók Táblázata</h1>
                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Vezeteknév</th>
                            <th>Keresztnév</th>
                            <th>Becenév</th>
                            <th>Kor</th>
                            <th>Ösztöndíj</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="class/student">
                            <tr>
                                <td><xsl:value-of select="@id"/></td>
                                <td><xsl:value-of select="vezeteknev"/></td>
                                <td><xsl:value-of select="keresztnev"/></td>
                                <td><xsl:value-of select="becenev"/></td>
                                <td><xsl:value-of select="kor"/></td>
                                <td><xsl:value-of select="osztondij"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>

        </html>
    </xsl:template>

</xsl:stylesheet>
