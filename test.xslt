<?xml version='1.0'?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="xml" />
	<xsl:template match="/">
		<xsl:apply-templates select="RH" />
	</xsl:template>

	<xsl:template match="RH">
		<CBIDlyStmtReqLogMsg>
			<GrpHdr>
				<MsgId>
					<xsl:value-of select="support_name" />
				</MsgId>
				<CreDtTm>
					<xsl:value-of select="creation_date" />
				</CreDtTm>
				<MsgRcpt>
					<Id>
						<OrgId>
							<Othr>
								<Id>
									<xsl:value-of select="recipient" />
								</Id>
							</Othr>
						</OrgId>
					</Id>
				</MsgRcpt>
				<AddtlInf>
					<xsl:value-of select="sender" />
				</AddtlInf>
			</GrpHdr>
			<xsl:for-each select="x61">
				<Stmt>
					<Id>
						<xsl:value-of select="/RH/stmt_id" />
					</Id>
					<ElctrncSeqNb>
						<xsl:value-of select="number" />
					</ElctrncSeqNb>
					<CreDtTm>
						<xsl:value-of select="/RH/creation_date" />
					</CreDtTm>
					<CpyDplctInd>
						<xsl:if test="reason = 93011">
							DUPL
						</xsl:if>
					</CpyDplctInd>
					<Acct>
						<Id>
							<IBAN>
								<xsl:value-of select="iban" />
							</IBAN>
						</Id>
						<Ccy>
							<xsl:value-of select="currency_code" />
						</Ccy>
						<Tp>
							<Prtry>
								<xsl:value-of select="account_type" />
							</Prtry>
						</Tp>
						<Nm>
							<xsl:value-of select="description" />
						</Nm>
						<Svcr>
							<FinInstnId>
								<ClrSysMmbId>
									<MmbId>
										<xsl:value-of select="abi" />
									</MmbId>
								</ClrSysMmbId>
							</FinInstnId>
						</Svcr>
					</Acct>
					<Bal>
						<Tp>
							<CdOrPrtry>
								<Cd>CLAV</Cd>
							</CdOrPrtry>
						</Tp>
						<Amt Ccy="">0.0</Amt>
						<CdtDbtInd>CRDT</CdtDbtInd>
						<Dt>
							<Dt>2001-01-01</Dt>
						</Dt>
					</Bal>
					<TxsSummry>
						<TtlNtries>
							<NbOfNtries>
								<xsl:value-of select="count(x62)" />
							</NbOfNtries>
						</TtlNtries>
					</TxsSummry>
					<xsl:for-each select="x62">
						<Ntry>
							<NtryRef>
								<xsl:value-of select="number" />
							</NtryRef>
							<Amt>
								<xsl:value-of select="amount" />
							</Amt>
							<CdtDbtInd>
								<xsl:if test="sign = C or sign = c">
									CRDT
								</xsl:if>
								<xsl:if test="sign = D or sign = d">
									DBIT
								</xsl:if>
							</CdtDbtInd>
							<Sts>BOOK</Sts>
							<BookgDt>
								<Dt>
									<xsl:value-of select="account_date" />
								</Dt>
							</BookgDt>
							<ValDt>
								<Dt>
									<xsl:value-of select="date" />
								</Dt>
							</ValDt>
							<AcctSvcrRef>
								<xsl:value-of select="bank_reference" />
							</AcctSvcrRef>
							<BkTxCd>
								<Prtry>
									<Cd><xsl:value-of select="reason_internal_reason" /></Cd>
								</Prtry>
							</BkTxCd>
						</Ntry>
					</xsl:for-each>
					<AddtlStmtInf>AddtlStmtInf</AddtlStmtInf>
				</Stmt>
			</xsl:for-each>
		</CBIDlyStmtReqLogMsg>
	</xsl:template>
</xsl:stylesheet>