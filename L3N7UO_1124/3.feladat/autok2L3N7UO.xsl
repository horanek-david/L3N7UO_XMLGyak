<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<h2>Rendszamok:</h2>
				<table border="1">
					<tr bgcolor="#9acd32">
						<th>Ar</th>
						<th>Rendszam</th>
					</tr>
					<xsl:for-each select = "autok/auto">
						<tr>
							<xsl:choose>
						        <xsl:when test="ar&gt; 30000 ">
						        	<td><xsl:value-of select="ar"/></td>
									<td><xsl:value-of select="@rsz"/></td>
						        </xsl:when>
					       </xsl:choose>
						</tr>
					</xsl:for-each>
				</table>
				<p>30000-nel nagyobb aru autok: <xsl:value-of select="count(autok/auto[ar/text()>'30000'])"/></p>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>