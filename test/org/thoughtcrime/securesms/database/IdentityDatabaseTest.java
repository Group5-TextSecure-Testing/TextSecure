package org.thoughtcrime.securesms.database;

import java.io.IOException;
import static java.nio.charset.StandardCharsets.US_ASCII;
import javax.crypto.spec.SecretKeySpec;
import android.content.Context;
import android.test.AndroidTestCase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import org.junit.Test;
import org.whispersystems.libaxolotl.ecc.ECPublicKey;
import org.whispersystems.libaxolotl.IdentityKey;
import org.whispersystems.libaxolotl.InvalidKeyException;
import org.thoughtcrime.securesms.crypto.MasterSecret;

public class IdentityDatabaseTest extends AndroidTestCase {
	
	/* There are, as far as I can tell, no implementations of this interface */
	private CursorFactory cursorFactory = null;

	public IdentityDatabase getDatabaseInstance() {
		return new IdentityDatabase(this.getContext(), new DatabaseHelper(this.getContext(), "test.Identity", cursorFactory, 0));
	}
	
	public MasterSecret getMasterSecret() {
		final byte[] aesBytes = new byte[16];
		final byte[] sha1Bytes = new byte[20];
		
		return new MasterSecret(
				new SecretKeySpec(aesBytes,  "AES"),
				new SecretKeySpec(sha1Bytes, "HmacSHA1")
		);
	}

    @Test
	public void testNewDatabaseIsEmpty() throws Exception {
		final IdentityDatabase d = this.getDatabaseInstance();
		final Cursor c = d.getIdentities();
		assertEquals(0, c.getCount());
	}

    @Test
	public void testOneElementDatabase_Count() throws Exception {
		final IdentityKey key = new IdentityKey(new MyKey((byte) 42));
		final int id = 35709;
		final IdentityDatabase d = this.getDatabaseInstance();
		d.saveIdentity(this.getMasterSecret(), id, key);
		final Cursor c = d.getIdentities();
		assertEquals(1, c.getCount());
	}

    @Test
	public void testOneElementDatabase_Key() throws Exception {
		final IdentityKey key = new IdentityKey(new MyKey((byte) 42));
		final MasterSecret secret = this.getMasterSecret();
		final int id = 72364;
		final IdentityDatabase d = this.getDatabaseInstance();
		d.saveIdentity(secret, id, key);
		final Cursor c = d.getIdentities();
		final IdentityDatabase.Reader r = d.readerFor(secret, c);
		final IdentityDatabase.Identity identity = r.getCurrent();
		assertEquals(key, identity.getIdentityKey());
	}
	
	// I see nowhere to set Recipients
	
	

	public class MyKey implements ECPublicKey {
		final byte value;
		public MyKey(byte value) {
			this.value = value;
		}
		
		/** I have no idea what this means */
		@Override
		public int getType() {return 0;}
		
		@Override
		public byte[] serialize() {
			final byte[] asfd = new byte[ECPublicKey.KEY_SIZE];
			asfd[0] = value;
			return asfd;
		}
		
		public int compareTo(ECPublicKey other) {
			byte[] thisSer = this.serialize();
			byte[] otherSer = other.serialize();
			
			for (int i = 0; i < ECPublicKey.KEY_SIZE; i++) {
				if (thisSer[i] != otherSer[i]) {
					// exit out of loop
					return thisSer[i] - otherSer[i];
				} else {
					// go to next iteration of loop 
				}
			}
			return 0; // if items are equal
		}
	}
	
	
	private static class DatabaseHelper extends SQLiteOpenHelper {
		public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
			super(context, name, factory, version);
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(IdentityDatabase.CREATE_TABLE);
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
	}
}
